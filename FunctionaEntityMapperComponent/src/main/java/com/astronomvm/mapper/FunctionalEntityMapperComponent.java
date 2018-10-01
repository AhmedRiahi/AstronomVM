package com.astronomvm.mapper;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.meta.ComponentMeta;

public class FunctionalEntityMapperComponent extends BaseComponent {


    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";
    private static final String FUNCTIONAL_ENTITY_PARAMETER_NAME = "FUNCTIONAL_ENTITY";


    @Override
    public ComponentMeta getComponentMeta() {
        return null;
    }

    @Override
    public void readInputs() {

    }

    @Override
    public ResultFlow execute() throws ComponentException {
        return null;
    }
}
