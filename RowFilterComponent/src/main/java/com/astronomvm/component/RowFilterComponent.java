package com.astronomvm.component;

import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.data.output.ResultSet;
import com.astronomvm.core.model.data.row.AstronomObject;
import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.data.row.Row;
import com.astronomvm.core.model.meta.component.ComponentMeta;
import com.astronomvm.core.model.meta.component.ComponentParameterMeta;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


public class RowFilterComponent extends AstronomBaseComponent {

    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";
    private static final String VALID_OUTPUT_FLOW_NAME_PARAMETER_NAME = "VALID_OUTPUT_FLOW_NAME";
    private static final String INVALID_OUTPUT_FLOW_NAME_PARAMETER_NAME = "INVALID_OUTPUT_FLOW_NAME";
    private static final String FILTER_COLUMN_PARAMETER_NAME = "FILTER_COLUMN";
    private static final String FILTER_OPERATOR_PARAMETER_NAME = "FILTER_OPERATOR";
    private static final String FILTER_VALUE_PARAMETER_NAME = "FILTER_VALUE";

    private String filterColumnName;
    private String filterOperatorString;
    private String filterValueString;
    private String validOutputFlowParameterName;
    private String invalidOutputFlowParameterName;
    private ResultSet inputFlowResultSet;


    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("ROW_FILTER");

        ComponentParameterMeta filterColumnParameterMeta = new ComponentParameterMeta();
        filterColumnParameterMeta.setName(FILTER_COLUMN_PARAMETER_NAME);
        filterColumnParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta filterOperatorParameterMeta = new ComponentParameterMeta();
        filterOperatorParameterMeta.setName(FILTER_OPERATOR_PARAMETER_NAME);
        filterOperatorParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta filterValueParameterMeta = new ComponentParameterMeta();
        filterValueParameterMeta.setName(FILTER_VALUE_PARAMETER_NAME);
        filterValueParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta inputFlowNameParameterMeta = new ComponentParameterMeta();
        inputFlowNameParameterMeta.setName(INPUT_FLOW_NAME_PARAMETER_NAME);
        inputFlowNameParameterMeta.setType(DataType.INPUT_FLOW_NAME);

        ComponentParameterMeta validOutputFlowParameterMeta = new ComponentParameterMeta();
        validOutputFlowParameterMeta.setName(VALID_OUTPUT_FLOW_NAME_PARAMETER_NAME);
        validOutputFlowParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta invalidOutputFlowParameterMeta = new ComponentParameterMeta();
        invalidOutputFlowParameterMeta.setName(INVALID_OUTPUT_FLOW_NAME_PARAMETER_NAME);
        invalidOutputFlowParameterMeta.setType(DataType.STRING);

        componentMeta.addParameterMeta(filterColumnParameterMeta);
        componentMeta.addParameterMeta(filterOperatorParameterMeta);
        componentMeta.addParameterMeta(filterValueParameterMeta);
        componentMeta.addParameterMeta(inputFlowNameParameterMeta);
        componentMeta.addParameterMeta(validOutputFlowParameterMeta);
        componentMeta.addParameterMeta(invalidOutputFlowParameterMeta);
        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues){
        String inputFlowParameterName = parametersValues.get(INPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.inputFlowResultSet = this.getInputResultFlow().getResultSet(inputFlowParameterName);
        this.filterColumnName =  parametersValues.get(FILTER_COLUMN_PARAMETER_NAME).getString("value");
        this.filterOperatorString = parametersValues.get(FILTER_OPERATOR_PARAMETER_NAME).getString("value");
        this.filterValueString =  parametersValues.get(FILTER_VALUE_PARAMETER_NAME).getString("value");
        this.validOutputFlowParameterName =  parametersValues.get(VALID_OUTPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.invalidOutputFlowParameterName =  parametersValues.get(INVALID_OUTPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
    }

    @Override
    public ResultFlow execute() {

        BiFunction<AstronomObject,AstronomObject,Boolean> comparisonOperation = buildComparisonOperation(this.filterOperatorString);
        Integer filterColumnIndex = this.inputFlowResultSet.getRowHeader().getColumnNameIndex(this.filterColumnName);
        AstronomObject filterValue = new AstronomObject(this.filterValueString);

        Map<Boolean,List<Row>> filterGroups =  this.inputFlowResultSet.getRows().stream().collect(Collectors.groupingBy(row -> comparisonOperation.apply(row.getColumnAt(filterColumnIndex).getValue(), filterValue)));


        ResultSet validFilterResultSet = new ResultSet();
        validFilterResultSet.setRows(filterGroups.get(true) == null ? new ArrayList<>() : filterGroups.get(true));

        ResultSet invalidFilterResultSet = new ResultSet();
        invalidFilterResultSet.setRows(filterGroups.get(false) == null ? new ArrayList<>() : filterGroups.get(false));

        ResultFlow resultFlow = new ResultFlow();
        resultFlow.addResultSet(this.validOutputFlowParameterName,validFilterResultSet);
        resultFlow.addResultSet(this.invalidOutputFlowParameterName,invalidFilterResultSet);

        return resultFlow;
    }

    private BiFunction<AstronomObject,AstronomObject,Boolean> buildComparisonOperation(String operation){
        switch (operation){
            case "=": return (AstronomObject a, AstronomObject b) -> a.equals(b);
            default: throw new UnsupportedOperationException();
        }
    }
}
