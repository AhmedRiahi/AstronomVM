package com.astronomvm.kernel.engine;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.data.input.InputParameters;

public class ComponentExecutor {

    private ComponentExecutionListener componentExecutionListener;


    public ComponentExecutor(ComponentExecutionListener componentExecutionListener){
        this.componentExecutionListener = componentExecutionListener;
    }

    public void execute(BaseComponent component, InputParameters inputParameters){
        component.setInputParameters(inputParameters);
        component.execute();
    }
}
