package com.astronomvm.kernel.engine.orchestra;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.input.InputParameter;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.output.ResultStorage;
import com.astronomvm.core.data.row.AstronomObject;
import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.meta.*;
import com.astronomvm.core.service.IComponentLogManager;
import com.astronomvm.kernel.engine.component.ComponentExecutor;
import com.astronomvm.kernel.workflow.AstronomWorkflow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Slf4j
@Data
public class Orchestrator {

    private ResultStorage resultStorage = new ResultStorage();
    private List<IOrchestraListener> orchestraListeners = new ArrayList<>();
    private IComponentLogManager componentLogManager;

    public void play(AstronomWorkflow workflow){
        this.orchestraListeners.parallelStream().forEach(IOrchestraListener::onOrchestraStartEvent);
        HashMap<Integer,List<StepMeta>> stepsIndex = this.buildWorkflowExecutionOrder(workflow.getMetaFlow());
        stepsIndex.keySet().forEach(level -> {
            List<StepMeta> steps = stepsIndex.get(level);
            steps.forEach(step -> this.executeStep(workflow,step));
        });
        this.orchestraListeners.parallelStream().forEach(IOrchestraListener::onOrchestraFinishEvent);
    }

    private HashMap<Integer,List<StepMeta>> buildWorkflowExecutionOrder(MetaFlow metaFlow){
        HashMap<Integer,List<StepMeta>> stepsIndex = new HashMap<>();
        List<StepMeta> currentSteps = metaFlow.getStepMetaList();
        List<TransitionMeta> currentTransitions = metaFlow.getTransitions();
        final AtomicInteger level = new AtomicInteger(0);
        while(!currentSteps.isEmpty()){
            List<StepMeta> processedSteps = new ArrayList<>();
            currentSteps.forEach(step -> {
                boolean stepInTargetTransition = currentTransitions.stream().anyMatch(transition -> transition.getTarget().equals(step));
                if(!stepInTargetTransition){
                    processedSteps.add(step);
                    currentTransitions.removeIf(transition -> transition.getSource().equals(step));
                    stepsIndex.putIfAbsent(level.get(),new ArrayList<>());
                    stepsIndex.get(level.get()).add(step);
                }
            });
            currentSteps.removeAll(processedSteps);
            level.incrementAndGet();
        }
        return stepsIndex;
    }


    private void executeStep(AstronomWorkflow workflow,StepMeta stepMeta){
        BaseComponent component = workflow.getComponentByName(stepMeta.getComponentName());
        ComponentExecutor componentExecutor = new ComponentExecutor();
        componentExecutor.setComponentLogManager(this.componentLogManager);
        try {
            this.prepareResultFlowParameterInputs(workflow,stepMeta);
            ResultFlow resultFlow = componentExecutor.execute(component,stepMeta.getInputParameters());
            this.resultStorage.addStepResultFlow(stepMeta.getName(),resultFlow);
        } catch (ComponentException e) {
            log.error(e.getMessage(),e);
        }
    }


    private void prepareResultFlowParameterInputs(AstronomWorkflow workflow,StepMeta stepMeta){
        List<StepMeta> sourceSteps = workflow.getMetaFlow().getSourceSteps(stepMeta);
        ComponentMeta componentMeta = workflow.getComponentByName(stepMeta.getComponentName()).getComponentMeta();
        List<ParameterMeta> inputFlowParameterMetas = componentMeta.getParameterMetas().stream().filter(parameterMeta -> parameterMeta.getType().equals(DataType.INPUT_FLOW_NAME)).collect(Collectors.toList());
        inputFlowParameterMetas.stream().forEach(parameterMeta -> {
            String inputFlowName = stepMeta.getInputParameters().getParameterByName(parameterMeta.getName()).getValue().toString();
            Map<String,ResultSet> resultSetMap = sourceSteps.stream().map(sourceStepMeta -> this.resultStorage.getStepMetaResultFlow(sourceStepMeta.getName()).getResultSetMap()).filter(map -> map.containsKey(inputFlowName)).findAny().get();
            ResultSet inputFlowResultSet = resultSetMap.get(inputFlowName);
            stepMeta.getInputParameters().addParameter(new InputParameter(inputFlowName,new AstronomObject(inputFlowResultSet)));
        });
    }

    public void subscribeOrchestraListener(IOrchestraListener orchestraListener){
        this.orchestraListeners.add(orchestraListener);
    }

    public void registerComponentLogManager(IComponentLogManager componentLogManager){
        this.componentLogManager = componentLogManager;
    }

}
