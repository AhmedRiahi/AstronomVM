package com.astronomvm.kernel.factory;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.kernel.exception.ComponentCreationException;
import com.astronomvm.kernel.spi.ComponentsRegistryBoard;
import com.astronomvm.kernel.workflow.AstronomWorkflow;

public class WokflowBuilder {


    private static final WokflowBuilder instance = new WokflowBuilder();

    private WokflowBuilder(){}

    public static WokflowBuilder getInstance(){
        return WokflowBuilder.instance;
    }

    public static AstronomWorkflow buildWorkflow(MetaFlow astronomMetaFlow){
        AstronomWorkflow astronomWorkflow = new AstronomWorkflow(astronomMetaFlow);
        astronomMetaFlow.getStepMetaList().stream().forEach(step -> {
            try {
                BaseComponent component = ComponentFactory.getInstance().buildComponent(step.getComponentName());
                astronomWorkflow.addComponent(component.getComponentMeta().getName(),component);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new ComponentCreationException();
            }
        });

        return astronomWorkflow;
    }
}
