package com.astronomvm.sqlExecutor;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.data.row.AstronomObject;
import com.astronomvm.core.model.data.row.Column;
import com.astronomvm.core.model.data.row.Row;
import com.astronomvm.core.model.data.row.RowHeader;
import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.database.DatabaseConnectionProperties;
import com.astronomvm.core.model.meta.ComponentMeta;
import com.astronomvm.core.model.meta.ParameterMeta;
import org.json.JSONObject;

import java.sql.*;
import java.util.Map;
import java.util.stream.IntStream;

public class SQLExecutorComponent extends AstronomBaseComponent {

    private static final String SQL_QUERY_PARAMETER_NAME = "SQL_QUERY";
    private static final String DATABASE_CONNECTION_PROPERTIES_PARAMETER_NAME = "DTATABASE_CONNECTION_PROPERTIES";


    private String sqlQuery;
    private DatabaseConnectionProperties databaseConnectionProperties;


    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("SQL_EXECUTOR");

        ParameterMeta sqlQueryParameterMeta = new ParameterMeta();
        sqlQueryParameterMeta.setName(SQL_QUERY_PARAMETER_NAME);
        sqlQueryParameterMeta.setType(DataType.STRING);

        ParameterMeta databaseConnectionPropertiesParameterMeta = new ParameterMeta();
        databaseConnectionPropertiesParameterMeta.setName(DATABASE_CONNECTION_PROPERTIES_PARAMETER_NAME);
        databaseConnectionPropertiesParameterMeta.setType(DataType.DATABASE_CONNECTION_PROPERTIES);

        componentMeta.addParameterMeta(sqlQueryParameterMeta);
        componentMeta.addParameterMeta(databaseConnectionPropertiesParameterMeta);

        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {

    }

    @Override
    public ResultFlow execute() throws ComponentException {
        ResultFlow resultFlow = new ResultFlow();

        try {
            Class.forName(this.databaseConnectionProperties.getJdbcDriver().getClassName());
            Connection connection = DriverManager.getConnection(this.databaseConnectionProperties.getDatabaseUrl(),this.databaseConnectionProperties.getUsername(),this.databaseConnectionProperties.getPassword());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(this.sqlQuery);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            com.astronomvm.core.model.data.output.ResultSet astronomResultSet = new com.astronomvm.core.model.data.output.ResultSet();
            RowHeader rowHeader = new RowHeader();
            astronomResultSet.setRowHeader(rowHeader);
            IntStream.range(0,resultSetMetaData.getColumnCount()).forEach(columnIndex -> {
                try {
                    String columnName = resultSetMetaData.getColumnName(columnIndex);
                    rowHeader.addColumn(columnName,DataType.STRING);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            while (resultSet.next()){
                Row row = new Row();
                IntStream.range(0,resultSetMetaData.getColumnCount()).forEach(columnIndex -> {
                    try {
                        Object value = resultSet.getObject(columnIndex);
                        Column column = new Column();
                        column.setValue(new AstronomObject(value));
                        row.addColumn(column);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                astronomResultSet.addRow(row);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ComponentException(e);
        }
        return resultFlow;
    }
}
