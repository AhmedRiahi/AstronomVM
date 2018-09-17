package com.astronomvm.kernel.engine;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.data.input.InputParameters;
import com.astronomvm.core.data.output.ResultSet;

import java.util.Optional;

public class ComponentExecutor {

    public Optional<ResultSet> execute(BaseComponent component, InputParameters inputParameters){
        component.setInputParameters(inputParameters);
        return component.execute();
    }
}
