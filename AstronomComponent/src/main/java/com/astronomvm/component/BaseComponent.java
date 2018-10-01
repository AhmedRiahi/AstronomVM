package com.astronomvm.component;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.input.InputParameters;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.service.IComponentLogManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public abstract class BaseComponent {

    protected InputParameters inputParameters;
    private IComponentLogManager componentLogManager;

    public abstract ComponentMeta getComponentMeta();
    public abstract void readInputs();
    public abstract ResultFlow execute() throws ComponentException;

    public void log(String message){
        this.componentLogManager.log(message);
    }
}
