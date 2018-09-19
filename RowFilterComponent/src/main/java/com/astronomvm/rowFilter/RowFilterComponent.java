package com.astronomvm.rowFilter;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.row.DataType;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;


public class RowFilterComponent extends BaseComponent {

    private static final String FIRST_FLOW_PARAMETER_NAME = "FIRST_FLOW";
    private static final String SECOND_FLOW_PARAMETER_NAME = "SECOND_FLOW";

    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("ROW_FILTER");

        ParameterMeta firstFlowParameterMeta = new ParameterMeta();
        firstFlowParameterMeta.setName(FIRST_FLOW_PARAMETER_NAME);
        firstFlowParameterMeta.setType(DataType.RESULT_SET);

        ParameterMeta secondFlowParameterMeta = new ParameterMeta();
        secondFlowParameterMeta.setName(SECOND_FLOW_PARAMETER_NAME);
        secondFlowParameterMeta.setType(DataType.RESULT_SET);

        componentMeta.addParameterMeta(firstFlowParameterMeta);
        componentMeta.addParameterMeta(secondFlowParameterMeta);
        return componentMeta;
    }

    @Override
    public ResultFlow execute() throws ComponentException {
        return null;
    }
}
