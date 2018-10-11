package com.astronomvm.kernel.factory;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.core.model.meta.MetaFlow;
import com.astronomvm.kernel.exception.ComponentCreationException;
import com.astronomvm.kernel.model.workflow.AstronomWorkflow;

public class WokflowBuilder {


    private static final WokflowBuilder instance = new WokflowBuilder();

    private WokflowBuilder(){}

    public static WokflowBuilder getInstance(){
        return WokflowBuilder.instance;
    }

    public static AstronomWorkflow buildWorkflow(MetaFlow metaFlow){
        AstronomWorkflow astronomWorkflow = new AstronomWorkflow(metaFlow);
        metaFlow.getStepMetaList().stream().forEach(step -> {
            try {
                AstronomBaseComponent component = ComponentFactory.getInstance().buildComponent(step.getComponentName());
                astronomWorkflow.addComponent(component.getComponentMeta().getName(),component);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new ComponentCreationException();
            }
        });

        return astronomWorkflow;
    }
}
