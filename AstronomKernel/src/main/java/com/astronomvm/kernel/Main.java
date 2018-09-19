package com.astronomvm.kernel;

import com.astronomvm.core.data.input.InputParameter;
import com.astronomvm.core.data.row.AstronomObject;
import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;
import com.astronomvm.core.meta.StepMeta;
import com.astronomvm.kernel.engine.AstronomEngine;
import com.astronomvm.kernel.spi.ComponentsLoader;

public class Main {


    public static void main(String[] args){
        ComponentsLoader.getInstance().loadComponents();

        AstronomMetaFlow astronomMetaFlow = new AstronomMetaFlow();

        astronomMetaFlow.addStepMeta(buildCSVLoaderMetaStep());
        AstronomEngine.getInstance().executeWorkflow(astronomMetaFlow);
    }

    public static StepMeta buildCSVLoaderMetaStep(){
        StepMeta stepMeta = new StepMeta();
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("CSV_FILE_LOADER");

        stepMeta.getInputParameters().addParameter(new InputParameter("FILE_PATH",new AstronomObject("C:\\astronomvm\\test.txt")));
        stepMeta.getInputParameters().addParameter(new InputParameter("SEPARATOR",new AstronomObject(";")));
        stepMeta.getInputParameters().addParameter(new InputParameter("OUTPUT_FLOW",new AstronomObject("csv_flow")));
        stepMeta.setComponentMeta(componentMeta);
        return stepMeta;
    }
}
