package com.astronomvm.kernel.factory;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.kernel.exception.ComponentClassNotFoundException;
import com.astronomvm.kernel.exception.ComponentCreationException;
import com.astronomvm.kernel.model.workflow.AstronomWorkflow;

public class WorkflowBuilder {


    private static final WorkflowBuilder instance = new WorkflowBuilder();

    private WorkflowBuilder(){}

    public static WorkflowBuilder getInstance(){
        return WorkflowBuilder.instance;
    }

    public static AstronomWorkflow buildWorkflow(MetaFlow metaFlow){
        AstronomWorkflow astronomWorkflow = new AstronomWorkflow(metaFlow);
        metaFlow.getStepMetaList().stream().forEach(step -> {
            try {
                AstronomBaseComponent component = ComponentFactory.getInstance().buildComponent(step.getComponentName());
                astronomWorkflow.addComponent(component.getComponentMeta().getName(),component);
            } catch (IllegalAccessException | InstantiationException | ComponentClassNotFoundException e) {
                throw new ComponentCreationException(step.getComponentName());
            }
        });
        return astronomWorkflow;
    }
}
