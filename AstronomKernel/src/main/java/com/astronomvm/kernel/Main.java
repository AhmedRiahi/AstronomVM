package com.astronomvm.kernel;

import com.astronomvm.core.data.input.InputParameter;
import com.astronomvm.core.data.row.AstronomObject;
import com.astronomvm.core.meta.*;
import com.astronomvm.kernel.engine.AstronomEngine;
import com.astronomvm.kernel.spi.ComponentsLoader;

public class Main {


    public static void main(String[] args){
        ComponentsLoader.getInstance().loadComponents();

        AstronomMetaFlow astronomMetaFlow = new AstronomMetaFlow();

        StepMeta csvStepMeta = buildCSVLoaderMetaStep();
        StepMeta rowFilterStepMeta = buildRowFilterMetaStep();
        StepMeta textOutputStepMeta = buildTextOutputMetaStep();

        astronomMetaFlow.addStepMeta(csvStepMeta);
        astronomMetaFlow.addStepMeta(rowFilterStepMeta);
        astronomMetaFlow.addStepMeta(textOutputStepMeta);
        astronomMetaFlow.addTransition(new Transition(csvStepMeta,rowFilterStepMeta));
        astronomMetaFlow.addTransition(new Transition(rowFilterStepMeta,textOutputStepMeta));
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

    public static StepMeta buildRowFilterMetaStep(){
        StepMeta stepMeta = new StepMeta();
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("ROW_FILTER");

        stepMeta.getInputParameters().addParameter(new InputParameter("FILTER_COLUMN",new AstronomObject("Name")));
        stepMeta.getInputParameters().addParameter(new InputParameter("FILTER_OPERATOR",new AstronomObject("=")));
        stepMeta.getInputParameters().addParameter(new InputParameter("FILTER_VALUE",new AstronomObject("Astro")));
        stepMeta.getInputParameters().addParameter(new InputParameter("INPUT_FLOW_NAME",new AstronomObject("csv_flow")));
        stepMeta.setComponentMeta(componentMeta);
        return stepMeta;
    }

    public static StepMeta buildTextOutputMetaStep(){
        StepMeta stepMeta = new StepMeta();
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("TEXT_FILE_OUTPUT");

        stepMeta.getInputParameters().addParameter(new InputParameter("FILE_PATH",new AstronomObject("C:\\astronomvm\\out.txt")));
        stepMeta.getInputParameters().addParameter(new InputParameter("SEPARATOR",new AstronomObject(";")));
        stepMeta.getInputParameters().addParameter(new InputParameter("INPUT_FLOW",new AstronomObject("csv_flow")));
        stepMeta.setComponentMeta(componentMeta);
        return stepMeta;
    }


}
