package com.astronomvm.kernel.engine;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.data.input.InputParameters;

public class ComponentExecutor {

    public void execute(BaseComponent component, InputParameters inputParameters){
        component.setInputParameters(inputParameters);
        component.execute();
    }
}
