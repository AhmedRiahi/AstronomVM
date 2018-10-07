package com.astronomvm.kernel.engine.component;

import com.astronomvm.component.AstronomBaseComponent;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.EnvironmentVariables;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.service.IComponentLogManager;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Data
public class ComponentExecutor {

    private IComponentLogManager logger;

    public ResultFlow execute(AstronomBaseComponent component, ResultFlow inputResultFlow, String parametersValues, EnvironmentVariables environmentVariables) throws ComponentException {
        component.setInputResultFlow(inputResultFlow);
        component.setLogger(this.logger);
        FunctionalModelMetaRepository functionalModelMetaRepository = new FunctionalModelMetaRepository(environmentVariables.getFunctionalRepositoryServiceUrl());
        component.setFunctionalModelMetaRepository(functionalModelMetaRepository);
        logger.log("Start Reading step inputs");
        Map<String,JSONObject> componentInputParameters = this.prepareComponentInputParameters(parametersValues);
        component.parseInputParameters(componentInputParameters);
        logger.log("Finished Reading step inputs");
        return component.execute();
    }

    private Map<String,JSONObject> prepareComponentInputParameters(String parametersValues){
        Map<String,JSONObject> parametersMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject(parametersValues);
        JSONArray parametersJsonArray = jsonObject.getJSONArray("parameters");
        for(int i=0; i< parametersJsonArray.length(); i++){
            String parametersName = parametersJsonArray.getJSONObject(i).getString("name");
            parametersMap.put(parametersName,parametersJsonArray.getJSONObject(i));
        }
        return parametersMap;
    }
}
