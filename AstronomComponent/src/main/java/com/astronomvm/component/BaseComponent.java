package com.astronomvm.component;

import com.astronomvm.core.data.input.InputParameters;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.meta.ComponentMeta;
import lombok.Data;

import java.util.Optional;

@Data
public abstract class BaseComponent {

    protected InputParameters inputParameters;

    public abstract ComponentMeta getComponentMeta();
    public abstract Optional<ResultSet> execute();
}
