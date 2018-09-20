package com.astronomvm.csvFileLoader;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.row.*;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CSVFileLoaderComponent extends BaseComponent {

    private static final String FILE_PATH_PARAMETER_NAME = "FILE_PATH";
    private static final String SEPARATOR_PARAMETER_NAME = "SEPARATOR";
    private static final String OUTPUT_FLOW_NAME_PARAMETER_NAME = "OUTPUT_FLOW";
    private static final String ROW_HEADER_PARAMETER_NAME = "ROW_HEADER";

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

        componentMeta.addParameterMeta(filePathParameterMeta);
        componentMeta.addParameterMeta(separatorParameterMeta);
        componentMeta.addParameterMeta(outputFlowParameterMeta);
        return componentMeta;
    }

    public ResultFlow execute() throws ComponentException{
        ResultSet resultSet = new ResultSet();
        String filePath = this.inputParameters.getParameterByName(FILE_PATH_PARAMETER_NAME).getValue().toString();
        String separator = this.inputParameters.getParameterByName(SEPARATOR_PARAMETER_NAME).getValue().toString();
        String outputFlowName = this.inputParameters.getParameterByName(OUTPUT_FLOW_NAME_PARAMETER_NAME).getValue().toString();
        RowHeader rowHeader = (RowHeader) this.inputParameters.getParameterByName(ROW_HEADER_PARAMETER_NAME).getValue().getUnderlying();
        resultSet.setRowHeader(rowHeader);
        try {
            Stream<String> stream = Files.lines(Paths.get(filePath));
            stream.forEachOrdered(line -> {
                Row row = new Row();
                String[] cols = line.split(separator);
                final AtomicInteger counter = new AtomicInteger();
                Arrays.stream(cols).forEachOrdered(col -> {
                    Column column = new Column();
                    column.setValue(new AstronomObject(col));
                    row.addColumn(column);
                });
                resultSet.addRow(row);
            });
        } catch (IOException e) {
            throw new ComponentException(e.getMessage());
        }

        ResultFlow resultFlow = new ResultFlow();
        resultFlow.addResultSet(outputFlowName,resultSet);
        return resultFlow;
    }


}
