package com.astronomvm.component;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.data.row.AstronomObject;
import com.astronomvm.core.model.data.row.Column;
import com.astronomvm.core.model.data.row.Row;
import com.astronomvm.core.model.data.row.RowHeader;
import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.meta.database.DatabaseConnectionProperties;
import com.astronomvm.core.model.meta.database.JDBCDriver;
import com.astronomvm.core.model.meta.component.ComponentMeta;
import com.astronomvm.core.model.meta.component.ComponentParameterMeta;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.sql.*;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
public class SQLExecutorComponent extends AstronomBaseComponent {

    private static final String SQL_QUERY_PARAMETER_NAME = "SQL_QUERY";
    private static final String DATABASE_CONNECTION_PROPERTIES_PARAMETER_NAME = "DATABASE_CONNECTION_PROPERTIES";


    private String sqlQuery;
    private DatabaseConnectionProperties dcp;


    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("SQL_EXECUTOR");

        ComponentParameterMeta sqlQueryParameterMeta = new ComponentParameterMeta();
        sqlQueryParameterMeta.setName(SQL_QUERY_PARAMETER_NAME);
        sqlQueryParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta databaseConnectionPropertiesParameterMeta = new ComponentParameterMeta();
        databaseConnectionPropertiesParameterMeta.setName(DATABASE_CONNECTION_PROPERTIES_PARAMETER_NAME);
        databaseConnectionPropertiesParameterMeta.setType(DataType.DATABASE_CONNECTION_PROPERTIES);

        componentMeta.addParameterMeta(sqlQueryParameterMeta);
        componentMeta.addParameterMeta(databaseConnectionPropertiesParameterMeta);

        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {
        this.sqlQuery = parametersValues.get(SQL_QUERY_PARAMETER_NAME).getString("value");
        this.dcp = new DatabaseConnectionProperties();
        JSONObject dcpJson = parametersValues.get(DATABASE_CONNECTION_PROPERTIES_PARAMETER_NAME).getJSONObject("value");
        this.dcp.setDatabaseUrl(dcpJson.getString("databaseUrl"));
        this.dcp.setJdbcDriver(JDBCDriver.valueOf(dcpJson.getString("jdbcDriver")));
        this.dcp.setPassword(dcpJson.getString("password"));
        this.dcp.setUsername(dcpJson.getString("username"));
        this.dcp.setSchemaName(dcpJson.getString("schemaName"));
    }

    @Override
    public ResultFlow execute() throws ComponentException {
        ResultFlow resultFlow = new ResultFlow();

        try {
            Class.forName(this.dcp.getJdbcDriver().getClassName());
            Connection connection = DriverManager.getConnection(this.dcp.getDatabaseUrl(),this.dcp.getUsername(),this.dcp.getPassword());
            try(Statement statement = connection.createStatement()){
                boolean isSelectQuery = statement.execute(this.sqlQuery);
                if(isSelectQuery){
                    try(ResultSet resultSet = statement.getResultSet()){
                        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                        com.astronomvm.core.model.data.output.ResultSet astronomResultSet = new com.astronomvm.core.model.data.output.ResultSet();
                        RowHeader rowHeader = new RowHeader();
                        astronomResultSet.setRowHeader(rowHeader);
                        IntStream.range(1,resultSetMetaData.getColumnCount()+1).forEach(columnIndex -> {
                            try {
                                String columnName = resultSetMetaData.getColumnName(columnIndex);
                                rowHeader.addColumn(columnName,DataType.STRING);
                            }catch (SQLException e) {
                                log.error(e.getMessage(),e);
                            }
                        });
                        while (resultSet.next()){
                            Row row = new Row();
                            IntStream.range(1,resultSetMetaData.getColumnCount()+1).forEach(columnIndex -> {
                                try {
                                    Object value = resultSet.getObject(columnIndex);
                                    Column column = new Column();
                                    column.setValue(new AstronomObject(value));
                                    row.addColumn(column);
                                } catch (SQLException e) {
                                    log.error(e.getMessage(),e);
                                }
                            });
                            astronomResultSet.addRow(row);
                        }
                        resultFlow.addResultSet("result",astronomResultSet);
                    }

                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage(),e);
            throw new ComponentException(e);
        }
        return resultFlow;
    }
}
