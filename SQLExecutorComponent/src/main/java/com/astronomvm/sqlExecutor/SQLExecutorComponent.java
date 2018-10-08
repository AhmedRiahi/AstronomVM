package com.astronomvm.sqlExecutor;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;
import org.json.JSONObject;

import java.util.Map;

public class SQLExecutorComponent extends AstronomBaseComponent {

    private static final String SQL_QUERY_PARAMETER_NAME = "SQL_QUERY";
    private static final String DATABASE_CONNECTION_PROPERTIES_PARAMETER_NAME = "DTATABASE_CONNECTION_PROPERTIES";


    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("SQL_EXECUTOR");

        ParameterMeta sqlQueryParameterMeta = new ParameterMeta();
        sqlQueryParameterMeta.setName(SQL_QUERY_PARAMETER_NAME);
        sqlQueryParameterMeta.setType(DataType.STRING);

        ParameterMeta databaseConnectionPropertiesParameterMeta = new ParameterMeta();
        databaseConnectionPropertiesParameterMeta.setName(DATABASE_CONNECTION_PROPERTIES_PARAMETER_NAME);
        databaseConnectionPropertiesParameterMeta.setType(DataType.DTABASE_CONNECTION_PROPERTIES);

        componentMeta.addParameterMeta(sqlQueryParameterMeta);
        componentMeta.addParameterMeta(databaseConnectionPropertiesParameterMeta);

        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {

    }

    @Override
    public ResultFlow execute() {
        return null;
    }
}
