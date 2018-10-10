package com.astronomvm.examples;

import com.astronomvm.core.model.data.EnvironmentVariables;
import com.astronomvm.core.model.meta.MetaFlow;
import com.astronomvm.core.model.meta.StepMeta;
import com.astronomvm.kernel.engine.AstronomEngine;
import com.astronomvm.kernel.spi.ComponentsLoader;

public class SQLExecutorExample {


    public static void main(String[] args){

        ComponentsLoader.getInstance().loadComponents();

        MetaFlow astronomMetaFlow = new MetaFlow();

        StepMeta sqlExecutorStepMeta = buildSQLExecutorMetaStep();

        astronomMetaFlow.addStepMeta(sqlExecutorStepMeta);

        EnvironmentVariables environmentVariables = new EnvironmentVariables();
        environmentVariables.setFunctionalRepositoryServiceUrl("http://localhost:11000/");
        astronomMetaFlow.setEnvironmentVariables(environmentVariables);

        AstronomEngine.getInstance().executeWorkflow(astronomMetaFlow);
    }

    public static StepMeta buildSQLExecutorMetaStep(){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName("SQL_EXECUTOR");
        stepMeta.setParametersValues("{\n" +
                "    \"parameters\": [\n" +
                "        {\n" +
                "            \"name\": \"SQL_QUERY\",\n" +
                "            \"value\": \"update AstronomFLow set name='ok'\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"DATABASE_CONNECTION_PROPERTIES\",\n" +
                "            \"value\": {\n" +
                "            \t\"databaseUrl\":\"jdbc:postgresql://127.0.0.1:5432/AstronomDesigner\",\n" +
                "            \t\"jdbcDriver\":\"POSTGRES\",\n" +
                "            \t\"password\":\"root\",\n" +
                "            \t\"username\":\"postgres\",\n" +
                "            \t\"schemaName\":\"\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        stepMeta.setComponentName("SQL_EXECUTOR");
        return stepMeta;
    }
}
