package com.astronomvm.csvFileLoader;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.data.row.*;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CSVFileLoaderComponent extends BaseComponent {

    private static final String FILE_PATH_PARAMETER_NAME = "FILE_PATH";
    private static final String SEPARATOR_PARAMETER_NAME = "SEPARATOR";
    private static final String OUTPUT_FLOW_NAME_PARAMETER_NAME = "OUTPUT_FLOW_NAME";
    private static final String ROW_HEADER_COLUMNS_LENGTH_PARAMETER_NAME = "ROW_HEADER_COLUMNS_LENGTH";

    private String filePath;
    private String separator;
    private RowHeader rowHeader;
    private String outputFlowName;

    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("CSV_FILE_LOADER");

        ParameterMeta filePathParameterMeta = new ParameterMeta();
        filePathParameterMeta.setName(FILE_PATH_PARAMETER_NAME);
        filePathParameterMeta.setType(DataType.STRING);

        ParameterMeta separatorParameterMeta = new ParameterMeta();
        separatorParameterMeta.setName(SEPARATOR_PARAMETER_NAME);
        separatorParameterMeta.setType(DataType.STRING);

        ParameterMeta outputFlowParameterMeta = new ParameterMeta();
        outputFlowParameterMeta.setName(OUTPUT_FLOW_NAME_PARAMETER_NAME);
        outputFlowParameterMeta.setType(DataType.STRING);

        ParameterMeta rowHeaderParameterMeta = new ParameterMeta();
        rowHeaderParameterMeta.setName(ROW_HEADER_COLUMNS_LENGTH_PARAMETER_NAME);
        rowHeaderParameterMeta.setType(DataType.NUMBER);

        componentMeta.addParameterMeta(filePathParameterMeta);
        componentMeta.addParameterMeta(separatorParameterMeta);
        componentMeta.addParameterMeta(outputFlowParameterMeta);
        componentMeta.addParameterMeta(rowHeaderParameterMeta);
        return componentMeta;
    }


    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {

        this.filePath = parametersValues.get(FILE_PATH_PARAMETER_NAME).getString("value");
        this.separator = parametersValues.get(SEPARATOR_PARAMETER_NAME).getString("value");
        this.outputFlowName = parametersValues.get(OUTPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        Integer rowHeaderColumnsLength =  parametersValues.get(ROW_HEADER_COLUMNS_LENGTH_PARAMETER_NAME).getInt("value");
        this.rowHeader = new RowHeader();
        IntStream.range(1,rowHeaderColumnsLength+1).forEach(i-> this.rowHeader.addColumn("Col"+i,DataType.STRING));
    }

    @Override
    public ResultFlow execute() throws ComponentException{
        ResultSet resultSet = new ResultSet();
        resultSet.setRowHeader(this.rowHeader);

        try {
            try (Stream<String> stream = Files.lines(Paths.get(this.filePath))) {
                stream.forEachOrdered(line -> {
                    Row row = new Row();
                    String[] cols = line.split(this.separator);
                    Arrays.stream(cols).forEachOrdered(col -> {
                        Column column = new Column();
                        column.setValue(new AstronomObject(col));
                        row.addColumn(column);
                    });
                    resultSet.addRow(row);
                });
            }
        }catch(IOException e){
            throw new ComponentException(e.getMessage());
        }

        ResultFlow resultFlow = new ResultFlow();
        resultFlow.addResultSet(this.outputFlowName,resultSet);
        return resultFlow;
    }

}
