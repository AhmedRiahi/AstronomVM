package com.astronomvm.component;

import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.data.output.ResultSet;
import com.astronomvm.core.model.data.row.AstronomObject;
import com.astronomvm.core.model.data.row.Column;
import com.astronomvm.core.model.data.row.Row;
import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.meta.component.ComponentMeta;
import com.astronomvm.core.model.meta.component.ComponentParameterMeta;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonPathSelectorComponent extends AstronomBaseComponent {

    private static final String OUTPUT_FLOW_NAME_PARAMETER_NAME = "OUTPUT_FLOW_NAME";
    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";
    private static final String SELECTOR_PARAMETER_NAME = "SELECTOR";


    private ResultSet inputFlowResultSet;
    private String outputFlowName;
    private String jsonSelector;

    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("JSON_PATH_SELECTOR");

        ComponentParameterMeta outputFlowNameParameterMeta = new ComponentParameterMeta();
        outputFlowNameParameterMeta.setName(OUTPUT_FLOW_NAME_PARAMETER_NAME);
        outputFlowNameParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta inputFlowNameParameterMeta = new ComponentParameterMeta();
        inputFlowNameParameterMeta.setName(INPUT_FLOW_NAME_PARAMETER_NAME);
        inputFlowNameParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta selectorParameterMeta = new ComponentParameterMeta();
        selectorParameterMeta.setName(SELECTOR_PARAMETER_NAME);
        selectorParameterMeta.setType(DataType.STRING);

        componentMeta.addParameterMeta(inputFlowNameParameterMeta);
        componentMeta.addParameterMeta(outputFlowNameParameterMeta);
        componentMeta.addParameterMeta(selectorParameterMeta);

        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {
        String inputFlowName = parametersValues.get(INPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.inputFlowResultSet = this.getInputResultFlow().getResultSet(inputFlowName);
        this.outputFlowName = parametersValues.get(OUTPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.jsonSelector = parametersValues.get(SELECTOR_PARAMETER_NAME).getString("value");
    }

    @Override
    public ResultFlow execute() {
        ResultFlow resultFlow = new ResultFlow();
        Object jsonContent = this.inputFlowResultSet.getRows().get(0).getColumns().get(0).getValue().getUnderlying();
        DocumentContext jsonContext = JsonPath.parse(jsonContent);
        Object selectedObject = jsonContext.read(this.jsonSelector);
        ResultSet resultSet = new ResultSet();

        if(selectedObject instanceof ArrayList){
            ArrayList jsonArray = (ArrayList) selectedObject;
            jsonArray.stream().forEachOrdered(jsonElement -> {
                Row row = new Row();
                row.addColumn(new Column(new AstronomObject(jsonElement)));
                resultSet.addRow(row);
            });
        }else{
            Row row = new Row();
            row.addColumn(new Column(new AstronomObject(selectedObject)));
            resultSet.addRow(row);
        }

        resultFlow.addResultSet(this.outputFlowName,resultSet);
        return resultFlow;
    }
}
