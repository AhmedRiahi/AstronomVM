package com.astronomvm.kernel.engine.orchestra;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.output.ResultStorage;
import com.astronomvm.core.meta.*;
import com.astronomvm.core.service.IComponentLogManager;
import com.astronomvm.kernel.engine.component.ComponentExecutor;
import com.astronomvm.kernel.workflow.AstronomWorkflow;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Data
public class Orchestrator {

    private ResultStorage resultStorage = new ResultStorage();
    private List<IOrchestraListener> orchestraListeners = new ArrayList<>();
    private IComponentLogManager componentLogManager;
    private AstronomWorkflow workflow;

    public Orchestrator(AstronomWorkflow workflow){
        this.workflow = workflow;
    }

    public void play(){
        this.orchestraListeners.parallelStream().forEach(IOrchestraListener::onOrchestraStartEvent);
        HashMap<Integer,List<StepMeta>> stepsIndex = this.buildWorkflowExecutionOrder(this.workflow.getMetaFlow());
        stepsIndex.keySet().forEach(level -> {
            List<StepMeta> steps = stepsIndex.get(level);
            steps.forEach(this::executeStep);
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


    private void executeStep(StepMeta stepMeta){
        AstronomBaseComponent component = workflow.getComponentByName(stepMeta.getComponentName());
        ComponentExecutor componentExecutor = new ComponentExecutor();
        componentExecutor.setLogger(this.componentLogManager);
        try {
            ResultFlow inputResultFlow = this.prepareInputResultFlow(stepMeta);
            this.orchestraListeners.parallelStream().forEach(orchestraListener -> orchestraListener.onStepStartEvent(stepMeta.getName()));
            ResultFlow outputResultFlow = componentExecutor.execute(component,inputResultFlow,stepMeta.getParametersValues());
            this.resultStorage.addStepResultFlow(stepMeta.getName(),outputResultFlow);
            this.orchestraListeners.parallelStream().forEach(orchestraListener -> orchestraListener.onStepFinishEvent(stepMeta.getName()));
        } catch (ComponentException e) {
            log.error(e.getMessage(),e);
        }
    }


    private ResultFlow prepareInputResultFlow(StepMeta stepMeta){
        ResultFlow resultFlow = new ResultFlow();
        List<TransitionMeta> inputTransitions = this.workflow.getMetaFlow().getInputTransitions(stepMeta);
        inputTransitions.stream().forEach(transitionMeta ->{
            ResultSet inputResultSet = this.resultStorage.getStepMetaResultSet(transitionMeta.getSource().getName(),transitionMeta.getSourceFlowName());
            resultFlow.addResultSet(transitionMeta.getTargetFlowName(),inputResultSet);
        });
        return resultFlow;
    }

    public void subscribeOrchestraListener(IOrchestraListener orchestraListener){
        this.orchestraListeners.add(orchestraListener);
    }

    public void registerComponentLogManager(IComponentLogManager componentLogManager){
        this.componentLogManager = componentLogManager;
    }

}
