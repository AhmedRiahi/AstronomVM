package com.astronomvm.rowFilter;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.row.AstronomObject;
import com.astronomvm.core.data.row.DataType;
import com.astronomvm.core.data.row.Row;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


public class RowFilterComponent extends BaseComponent {

    private static final String INPUT_FLOW_PARAMETER_NAME = "INPUT_FLOW";
    private static final String FILTER_COLUMN_PARAMETER_NAME = "FILTER_COLUMN";
    private static final String FILTER_OPERATOR_PARAMETER_NAME = "FILTER_COLUMN";
    private static final String FILTER_VALUE_PARAMETER_NAME = "FILTER_VALUE";


    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("ROW_FILTER");

        ParameterMeta firstFlowParameterMeta = new ParameterMeta();
        firstFlowParameterMeta.setName(INPUT_FLOW_PARAMETER_NAME);
        firstFlowParameterMeta.setType(DataType.RESULT_SET);
        firstFlowParameterMeta.setUserInput(false);

        ParameterMeta filterColumnParameterMeta = new ParameterMeta();
        filterColumnParameterMeta.setName(FILTER_COLUMN_PARAMETER_NAME);
        filterColumnParameterMeta.setType(DataType.STRING);

        ParameterMeta filterOperatorParameterMeta = new ParameterMeta();
        filterOperatorParameterMeta.setName(FILTER_OPERATOR_PARAMETER_NAME);
        filterOperatorParameterMeta.setType(DataType.STRING);

        ParameterMeta filterValueParameterMeta = new ParameterMeta();
        filterValueParameterMeta.setName(FILTER_VALUE_PARAMETER_NAME);
        filterValueParameterMeta.setType(DataType.STRING);

        componentMeta.addParameterMeta(firstFlowParameterMeta);
        componentMeta.addParameterMeta(filterColumnParameterMeta);
        componentMeta.addParameterMeta(filterOperatorParameterMeta);
        componentMeta.addParameterMeta(filterValueParameterMeta);
        return componentMeta;
    }

    @Override
    public ResultFlow execute() throws ComponentException {
        ResultSet inputFlowResultSet = (ResultSet) this.inputParameters.getParameterByName(INPUT_FLOW_PARAMETER_NAME).getValue().getUnderlying();
        String filterColumnName =  this.inputParameters.getParameterByName(FILTER_COLUMN_PARAMETER_NAME).getValue().toString();
        String filterOperatorString = this.inputParameters.getParameterByName(FILTER_OPERATOR_PARAMETER_NAME).getValue().toString();
        String filterValueString =  this.inputParameters.getParameterByName(FILTER_VALUE_PARAMETER_NAME).getValue().toString();

        BiFunction<AstronomObject,AstronomObject,Boolean> comparisonOperation = buildComparisonOperation(filterOperatorString);
        Integer filterColumnIndex = inputFlowResultSet.getRowHeader().getColumnNameIndex(filterColumnName);
        AstronomObject filterValue = new AstronomObject(filterValueString);

        Map<Boolean,List<Row>> filterGroups =  inputFlowResultSet.getRows().stream().collect(Collectors.groupingBy((row) -> comparisonOperation.apply(row.getColumnAt(filterColumnIndex).getValue(), filterValue)));


        ResultSet validFilterResultSet = new ResultSet();
        validFilterResultSet.setRows(filterGroups.get(true));

        ResultSet invalidFilterResultSet = new ResultSet();
        invalidFilterResultSet.setRows(filterGroups.get(true));

        ResultFlow resultFlow = new ResultFlow();
        resultFlow.addResultSet("VALID",validFilterResultSet);
        resultFlow.addResultSet("INVALID",invalidFilterResultSet);

        return resultFlow;
    }

    private BiFunction<AstronomObject,AstronomObject,Boolean> buildComparisonOperation(String operation){
        switch (operation){
            case "=": return (AstronomObject a, AstronomObject b) -> a.equals(b);
            default: throw new UnsupportedOperationException();
        }
    }
}