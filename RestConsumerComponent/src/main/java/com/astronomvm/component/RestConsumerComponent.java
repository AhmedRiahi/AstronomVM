package com.astronomvm.component;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.data.output.ResultSet;
import com.astronomvm.core.model.data.row.AstronomObject;
import com.astronomvm.core.model.data.row.Column;
import com.astronomvm.core.model.data.row.Row;
import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.meta.component.ComponentMeta;
import com.astronomvm.core.model.meta.component.ComponentParameterMeta;
import org.json.JSONObject;

import java.util.Map;

public class RestConsumerComponent extends AstronomBaseComponent {

    private static final String OUTPUT_FLOW_NAME_PARAMETER_NAME = "OUTPUT_FLOW_NAME";
    private static final String SERVICE_URL_PARAMETER_NAME = "SERVER_URL";

    private String outputFlowName;
    private String serviceUrl;

    private RestRequester restRequester = new RestRequester();




    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("REST_CONSUMER");

        ComponentParameterMeta outputFlowNameParameterMeta = new ComponentParameterMeta();
        outputFlowNameParameterMeta.setName(OUTPUT_FLOW_NAME_PARAMETER_NAME);
        outputFlowNameParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta serviceURLParameterMeta = new ComponentParameterMeta();
        serviceURLParameterMeta.setName(SERVICE_URL_PARAMETER_NAME);
        serviceURLParameterMeta.setType(DataType.STRING);

        componentMeta.addParameterMeta(outputFlowNameParameterMeta);
        componentMeta.addParameterMeta(serviceURLParameterMeta);

        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {
        this.outputFlowName = parametersValues.get(OUTPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.serviceUrl = parametersValues.get(SERVICE_URL_PARAMETER_NAME).getString("value");
    }

    @Override
    public ResultFlow execute() {
        ResultFlow resultFlow = new ResultFlow();
        Object restResponse = this.restRequester.sendRequest(this.serviceUrl);
        ResultSet resultSet = new ResultSet();
        Row row = new Row();
        Column column = new Column();
        column.setValue(new AstronomObject(restResponse));
        row.addColumn(column);
        resultSet.addRow(row);
        resultFlow.addResultSet(this.outputFlowName,resultSet);
        return resultFlow;
    }
}
