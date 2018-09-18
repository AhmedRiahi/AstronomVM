package com.astronomvm.kernel.engine;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.output.ResultStorage;
import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.core.meta.StepMeta;
import com.astronomvm.core.meta.Transition;
import com.astronomvm.kernel.workflow.AstronomWorkflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class AstronomOrchestra {

    private ResultStorage resultStorage = new ResultStorage();


    public void play(AstronomWorkflow workflow){
        HashMap<Integer,List<StepMeta>> stepsIndex = this.buildWorkflowExecutionOrder(workflow.getAstronomMetaFlow());
        stepsIndex.values().forEach(level -> {
            List<StepMeta> steps = stepsIndex.get(level);
            steps.forEach(step ->this.executeStep(workflow,step));
        });
    }

    private HashMap<Integer,List<StepMeta>> buildWorkflowExecutionOrder(AstronomMetaFlow metaFlow){
        HashMap<Integer,List<StepMeta>> stepsIndex = new HashMap<>();
        List<StepMeta> currentSteps = metaFlow.getStepMetaList();
        List<Transition> currentTransitions = metaFlow.getTransitions();
        final AtomicInteger level = new AtomicInteger(0);
        while(!currentSteps.isEmpty()){
            currentSteps.forEach(step -> {
                boolean stepInTargetTransition = currentTransitions.stream().anyMatch(transition -> transition.getTarget().equals(step));
                if(!stepInTargetTransition){
                    currentSteps.remove(step);
                    currentTransitions.removeIf(transition -> transition.getSource().equals(step));
                    stepsIndex.putIfAbsent(level.get(),new ArrayList<>());
                    stepsIndex.get(level.get()).add(step);
                }
            });
            level.incrementAndGet();
        }
        return stepsIndex;
    }


    private void executeStep(AstronomWorkflow workflow,StepMeta step){
        BaseComponent component = workflow.getComponentByName(step.getComponentMeta().getName());
        ComponentExecutor componentExecutor = new ComponentExecutor();
        Optional<ResultSet> resultSetOption = componentExecutor.execute(component,step.getInputParameters());
        resultSetOption.ifPresent(result -> this.resultStorage.addStepResult(step,resultSetOption.get()));

    }

}
