package com.astronomvm.kernel.components;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.meta.component.ComponentMeta;
import org.json.JSONObject;

import java.util.Map;

public class TestComponent extends AstronomBaseComponent {
    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("TestComponent");
        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {

    }

    @Override
    public ResultFlow execute() throws ComponentException {
        return null;
    }
}
