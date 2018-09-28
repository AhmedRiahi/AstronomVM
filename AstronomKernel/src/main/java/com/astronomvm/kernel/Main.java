package com.astronomvm.kernel;

import com.astronomvm.core.data.input.InputParameter;
import com.astronomvm.core.data.row.AstronomObject;
import com.astronomvm.core.data.astonomType.DataType;
import com.astronomvm.core.data.row.RowHeader;
import com.astronomvm.core.meta.*;
import com.astronomvm.kernel.engine.AstronomEngine;
import com.astronomvm.kernel.spi.ComponentsLoader;

public class Main {


    public static void main(String[] args){
        ComponentsLoader.getInstance().loadComponents();

        MetaFlow astronomMetaFlow = new MetaFlow();

        StepMeta csvStepMeta = buildCSVLoaderMetaStep();
        StepMeta rowFilterStepMeta = buildRowFilterMetaStep();
        StepMeta textOutputStepMeta = buildTextOutputMetaStep();
        StepMeta invalidTextOutputStepMeta = buildInvalidTextOutputMetaStep();

        astronomMetaFlow.addStepMeta(csvStepMeta);
        astronomMetaFlow.addStepMeta(rowFilterStepMeta);
        astronomMetaFlow.addStepMeta(textOutputStepMeta);
        astronomMetaFlow.addStepMeta(invalidTextOutputStepMeta);

        astronomMetaFlow.addTransition(new TransitionMeta(csvStepMeta,rowFilterStepMeta));
        astronomMetaFlow.addTransition(new TransitionMeta(rowFilterStepMeta,textOutputStepMeta));
        astronomMetaFlow.addTransition(new TransitionMeta(rowFilterStepMeta,invalidTextOutputStepMeta));
        AstronomEngine.getInstance().executeWorkflow(astronomMetaFlow);
    }

    public static StepMeta buildCSVLoaderMetaStep(){
        StepMeta stepMeta = new StepMeta();

        stepMeta.getInputParameters().addParameter(new InputParameter("FILE_PATH",new AstronomObject("C:\\astronomvm\\test.txt")));
        stepMeta.getInputParameters().addParameter(new InputParameter("SEPARATOR",new AstronomObject(";")));
        stepMeta.getInputParameters().addParameter(new InputParameter("OUTPUT_FLOW",new AstronomObject("csv_flow")));
        RowHeader rowHeader = new RowHeader();
        rowHeader.addColumn("Name", DataType.INPUT_FLOW_NAME.STRING);
        rowHeader.addColumn("Job", DataType.INPUT_FLOW_NAME.STRING);
        stepMeta.getInputParameters().addParameter(new InputParameter("ROW_HEADER",new AstronomObject(rowHeader)));
        stepMeta.setComponentName("CSV_FILE_LOADER");
        return stepMeta;
    }

    public static StepMeta buildRowFilterMetaStep(){
        StepMeta stepMeta = new StepMeta();

        stepMeta.getInputParameters().addParameter(new InputParameter("FILTER_COLUMN",new AstronomObject("Name")));
        stepMeta.getInputParameters().addParameter(new InputParameter("FILTER_OPERATOR",new AstronomObject("=")));
        stepMeta.getInputParameters().addParameter(new InputParameter("FILTER_VALUE",new AstronomObject("Astro")));
        stepMeta.getInputParameters().addParameter(new InputParameter("INPUT_FLOW_NAME",new AstronomObject("csv_flow")));
        stepMeta.getInputParameters().addParameter(new InputParameter("VALID_OUTPUT_FLOW_NAME",new AstronomObject("valid_csv_flow")));
        stepMeta.getInputParameters().addParameter(new InputParameter("INVALID_OUTPUT_FLOW_NAME",new AstronomObject("invalid_csv_flow")));
        stepMeta.setComponentName("ROW_FILTER");
        return stepMeta;
    }

    public static StepMeta buildTextOutputMetaStep(){
        StepMeta stepMeta = new StepMeta();

        stepMeta.getInputParameters().addParameter(new InputParameter("FILE_PATH",new AstronomObject("C:\\astronomvm\\valid.txt")));
        stepMeta.getInputParameters().addParameter(new InputParameter("SEPARATOR",new AstronomObject(";")));
        stepMeta.getInputParameters().addParameter(new InputParameter("INPUT_FLOW_NAME",new AstronomObject("valid_csv_flow")));
        stepMeta.setComponentName("TEXT_FILE_OUTPUT");
        return stepMeta;
    }

    public static StepMeta buildInvalidTextOutputMetaStep(){
        StepMeta stepMeta = new StepMeta();

        stepMeta.getInputParameters().addParameter(new InputParameter("FILE_PATH",new AstronomObject("C:\\astronomvm\\invalid.txt")));
        stepMeta.getInputParameters().addParameter(new InputParameter("SEPARATOR",new AstronomObject(";")));
        stepMeta.getInputParameters().addParameter(new InputParameter("INPUT_FLOW_NAME",new AstronomObject("invalid_csv_flow")));
        stepMeta.setComponentName("TEXT_FILE_OUTPUT");
        return stepMeta;
    }


}
