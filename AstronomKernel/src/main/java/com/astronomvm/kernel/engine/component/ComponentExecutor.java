package com.astronomvm.kernel.engine.component;

import com.astronomvm.component.BaseComponent;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.input.InputParameters;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.service.IComponentLogManager;
import lombok.Data;

@Data
public class ComponentExecutor {

    private IComponentLogManager componentLogManager;

    public ResultFlow execute(BaseComponent component, InputParameters inputParameters) throws ComponentException {
        component.setInputParameters(inputParameters);
        component.setComponentLogManager(this.componentLogManager);
        return component.execute();
    }
}
