package com.astronomvm.kernel.engine.component;

import com.astronomvm.component.AstronomBaseComponent;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.service.IComponentLogManager;
import lombok.Data;
import org.json.JSONObject;

import java.util.Map;

@Data
public class ComponentExecutor {

    private IComponentLogManager logger;

    public ResultFlow execute(AstronomBaseComponent component, ResultFlow inputResultFlow, String parametersValues) throws ComponentException {
        component.setInputResultFlow(inputResultFlow);
        component.setLogger(this.logger);
        logger.log("Start Reading step inputs");
        Map<String,JSONObject> componentInputParameters = this.prepareComponentInputParameters(parametersValues);
        component.parseInputParameters(componentInputParameters);
        logger.log("Finished Reading step inputs");
        return component.execute();
    }

    private Map<String,JSONObject> prepareComponentInputParameters(String parametersValues){
        return null;
    }
}
