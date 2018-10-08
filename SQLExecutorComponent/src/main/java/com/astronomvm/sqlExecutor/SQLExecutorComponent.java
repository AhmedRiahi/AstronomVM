package com.astronomvm.sqlExecutor;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.meta.ComponentMeta;
import org.json.JSONObject;

import java.util.Map;

public class SQLExecutorComponent extends AstronomBaseComponent {

    @Override
    public ComponentMeta getComponentMeta() {
        return null;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {

    }

    @Override
    public ResultFlow execute() throws ComponentException {
        return null;
    }
}
