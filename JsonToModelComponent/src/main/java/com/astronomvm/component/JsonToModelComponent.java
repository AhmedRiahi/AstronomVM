package com.astronomvm.component;

import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.data.output.ResultSet;
import com.astronomvm.core.model.data.row.AstronomObject;
import com.astronomvm.core.model.data.row.Column;
import com.astronomvm.core.model.data.row.Row;
import com.astronomvm.core.model.data.row.RowHeader;
import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.meta.component.ComponentMeta;
import com.astronomvm.core.model.meta.component.ComponentParameterMeta;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonToModelComponent extends AstronomBaseComponent {

    private static final String OUTPUT_FLOW_NAME_PARAMETER_NAME = "OUTPUT_FLOW_NAME";
    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";


    private ResultSet inputFlowResultSet;
    private String outputFlowName;
    private String jsonSelector;

    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("JSON_TO_MODEL");

        ComponentParameterMeta outputFlowNameParameterMeta = new ComponentParameterMeta();
        outputFlowNameParameterMeta.setName(OUTPUT_FLOW_NAME_PARAMETER_NAME);
        outputFlowNameParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta inputFlowNameParameterMeta = new ComponentParameterMeta();
        inputFlowNameParameterMeta.setName(INPUT_FLOW_NAME_PARAMETER_NAME);
        inputFlowNameParameterMeta.setType(DataType.STRING);

        componentMeta.addParameterMeta(inputFlowNameParameterMeta);
        componentMeta.addParameterMeta(outputFlowNameParameterMeta);

        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {
        String inputFlowName = parametersValues.get(INPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.inputFlowResultSet = this.getInputResultFlow().getResultSet(inputFlowName);
        this.outputFlowName = parametersValues.get(OUTPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
    }

    @Override
    public ResultFlow execute() {
        ResultFlow resultFlow = new ResultFlow();
        ResultSet resultSet = new ResultSet();
        this.inputFlowResultSet.getRows().stream().forEachOrdered(row -> {
            Object object = row.getColumnAt(0).getValue().getUnderlying();
            if(object instanceof JSONObject){
                JSONObject jsonObject = (JSONObject) object;
                jsonObject.keySet();
            }else{
                if(object instanceof LinkedHashMap){
                    LinkedHashMap jsonHashMap = (LinkedHashMap) object;

                    RowHeader rowHeader = new RowHeader();
                    jsonHashMap.keySet().stream().forEach(key -> {
                        rowHeader.addColumn(key.toString(),DataType.UNKNOWN);
                    });

                    resultSet.setRowHeader(rowHeader);

                    Row rowResult = new Row();
                    jsonHashMap.values().stream().forEach(entry -> {
                        Column column = new Column(new AstronomObject(entry));
                        rowResult.addColumn(column);
                    });

                    resultSet.addRow(rowResult);
                }
            }
        });

        resultFlow.addResultSet(this.outputFlowName,resultSet);
        return resultFlow;
    }
}
