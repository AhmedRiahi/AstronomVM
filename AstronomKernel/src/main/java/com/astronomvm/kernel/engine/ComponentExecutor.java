package com.astronomvm.kernel.engine;

import com.astronomvm.component.BaseComponent;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.input.InputParameters;
import com.astronomvm.core.data.output.ResultFlow;


public class ComponentExecutor {

    public ResultFlow execute(BaseComponent component, InputParameters inputParameters) throws ComponentException {
        component.setInputParameters(inputParameters);
        return component.execute();
    }
}
