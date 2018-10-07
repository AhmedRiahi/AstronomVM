package com.astronomvm.examples;

import com.astronomvm.core.data.EnvironmentVariables;
import com.astronomvm.core.data.row.AstronomObject;
import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.data.row.RowHeader;
import com.astronomvm.core.meta.*;
import com.astronomvm.kernel.engine.AstronomEngine;
import com.astronomvm.kernel.spi.ComponentsLoader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {


    public static void main(String[] args){
        log.info("Example main class");
        ComponentsLoader.getInstance().loadComponents();

        MetaFlow astronomMetaFlow = new MetaFlow();

        StepMeta csvStepMeta = buildCSVLoaderMetaStep();
        StepMeta functionalModelMapperStepMeta = buildFunctionalModelMapperMetaStep();
        StepMeta rowFilterStepMeta = buildRowFilterMetaStep();
        StepMeta textOutputStepMeta = buildTextOutputMetaStep();
        StepMeta invalidTextOutputStepMeta = buildInvalidTextOutputMetaStep();

        astronomMetaFlow.addStepMeta(csvStepMeta);
        astronomMetaFlow.addStepMeta(functionalModelMapperStepMeta);
        astronomMetaFlow.addStepMeta(rowFilterStepMeta);
        astronomMetaFlow.addStepMeta(textOutputStepMeta);
        astronomMetaFlow.addStepMeta(invalidTextOutputStepMeta);

        astronomMetaFlow.addTransition(new TransitionMeta(csvStepMeta,functionalModelMapperStepMeta,"csv_flow","csv_flow"));
        astronomMetaFlow.addTransition(new TransitionMeta(csvStepMeta,rowFilterStepMeta,"csv_flow","csv_flow"));
        astronomMetaFlow.addTransition(new TransitionMeta(rowFilterStepMeta,textOutputStepMeta,"valid_csv_flow","valid_csv_flow"));
        astronomMetaFlow.addTransition(new TransitionMeta(rowFilterStepMeta,invalidTextOutputStepMeta,"invalid_csv_flow","invalid_csv_flow"));

        EnvironmentVariables environmentVariables = new EnvironmentVariables();
        environmentVariables.setFunctionalRepositoryServiceUrl("http://localhost:11000/");
        astronomMetaFlow.setEnvironmentVariables(environmentVariables);

        AstronomEngine.getInstance().executeWorkflow(astronomMetaFlow);
    }

    public static StepMeta buildCSVLoaderMetaStep(){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName("CSV_FILE_LOADER");
        stepMeta.setParametersValues("{\n" +
                "    \"parameters\": [\n" +
                "        {\n" +
                "            \"name\": \"FILE_PATH\",\n" +
                "            \"value\": \"C:/astronomvm/test.txt\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"SEPARATOR\",\n" +
                "            \"value\": \";\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"OUTPUT_FLOW_NAME\",\n" +
                "            \"value\": \"csv_flow\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"ROW_HEADER_COLUMNS_LENGTH\",\n" +
                "            \"value\": 2\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        stepMeta.setComponentName("CSV_FILE_LOADER");
        return stepMeta;
    }

    public static StepMeta buildRowFilterMetaStep(){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName("ROW_FILTER");
        stepMeta.setParametersValues("{\n" +
                "    \"parameters\": [\n" +
                "        {\n" +
                "            \"name\": \"FILTER_COLUMN\",\n" +
                "            \"value\": \"Col1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"FILTER_OPERATOR\",\n" +
                "            \"value\": \"=\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"FILTER_VALUE\",\n" +
                "            \"value\": \"Astro\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"INPUT_FLOW_NAME\",\n" +
                "            \"value\": \"csv_flow\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"VALID_OUTPUT_FLOW_NAME\",\n" +
                "            \"value\": \"valid_csv_flow\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"INVALID_OUTPUT_FLOW_NAME\",\n" +
                "            \"value\": \"invalid_csv_flow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        stepMeta.setComponentName("ROW_FILTER");
        return stepMeta;
    }

    public static StepMeta buildTextOutputMetaStep(){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName("VALID_TEXT_FILE_OUTPUT");
        stepMeta.setParametersValues("{\n" +
                "    \"parameters\": [\n" +
                "        {\n" +
                "            \"name\": \"FILE_PATH\",\n" +
                "            \"value\": \"C:/astronomvm/valid.txt\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"SEPARATOR\",\n" +
                "            \"value\": \";\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"INPUT_FLOW_NAME\",\n" +
                "            \"value\": \"valid_csv_flow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        stepMeta.setComponentName("TEXT_FILE_OUTPUT");
        return stepMeta;
    }

    public static StepMeta buildInvalidTextOutputMetaStep(){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName("INVALID_TEXT_FILE_OUTPUT");
        stepMeta.setParametersValues("{\n" +
                "    \"parameters\": [\n" +
                "        {\n" +
                "            \"name\": \"FILE_PATH\",\n" +
                "            \"value\": \"C:/astronomvm/invalid.txt\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"SEPARATOR\",\n" +
                "            \"value\": \";\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"INPUT_FLOW_NAME\",\n" +
                "            \"value\": \"invalid_csv_flow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        stepMeta.setComponentName("TEXT_FILE_OUTPUT");
        return stepMeta;
    }

    public static StepMeta buildFunctionalModelMapperMetaStep(){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName("FUNCTIONAL_MODEL_MAPPER");
        stepMeta.setParametersValues("{\n" +
                "    \"parameters\": [\n" +
                "        {\n" +
                "            \"name\": \"INPUT_FLOW_NAME\",\n" +
                "            \"value\": \"csv_flow\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"FUNCTIONAL_MODEL_REPOSITORY_NAME\",\n" +
                "            \"value\": \"Test\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"FUNCTIONAL_MODEL_META_NAME\",\n" +
                "            \"value\": \"TheModel\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        stepMeta.setComponentName("FUNCTIONAL_MODEL_MAPPER");
        return stepMeta;
    }


}
