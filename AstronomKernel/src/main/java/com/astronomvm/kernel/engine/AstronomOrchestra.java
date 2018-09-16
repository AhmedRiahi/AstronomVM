package com.astronomvm.kernel.engine;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.kernel.workflow.AstronomWorkflow;

public class AstronomOrchestra {

    private ComponentExecutor componentExecutor;


    public void play(AstronomWorkflow workflow){
        workflow.getStepMetaList().stream().forEach(step ->{
            BaseComponent component = workflow.getComponentByName(step.getComponentMeta().getName());
            this.componentExecutor.execute(component,step.getInputParameters());
        });
    }
}
