package com.astronomvm.kernel;

import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.StepMeta;
import com.astronomvm.kernel.engine.AstronomEngine;
import com.astronomvm.kernel.spi.ComponentsLoader;

public class Main {


    public static void main(String[] args){
        ComponentsLoader.getInstance().loadComponents();

        AstronomMetaFlow astronomMetaFlow = new AstronomMetaFlow();
        StepMeta stepMeta = new StepMeta();
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("CSV_FILE_LOADER");
        stepMeta.setComponentMeta(componentMeta);
        astronomMetaFlow.addStepMeta(stepMeta);
        AstronomEngine.getInstance().executeWorkflow(astronomMetaFlow);
    }
}
