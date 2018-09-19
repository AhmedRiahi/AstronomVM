package com.astronomvm.component;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.input.InputParameters;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.meta.ComponentMeta;
import lombok.Data;

@Data
public abstract class BaseComponent {

    protected InputParameters inputParameters;

    public abstract ComponentMeta getComponentMeta();
    public abstract ResultFlow execute() throws ComponentException;
}
