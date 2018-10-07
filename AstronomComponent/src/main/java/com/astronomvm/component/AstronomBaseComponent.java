package com.astronomvm.component;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;
import com.astronomvm.core.service.IComponentLogManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.Map;

@Data
@Slf4j
public abstract class AstronomBaseComponent {

    private IComponentLogManager logger;
    private ResultFlow inputResultFlow;

    public abstract ComponentMeta getComponentMeta();
    public abstract void parseInputParameters(Map<String, JSONObject> parametersValues);
    public abstract ResultFlow execute() throws ComponentException;

    public void log(String message){
        this.logger.log(message);
    }
}
