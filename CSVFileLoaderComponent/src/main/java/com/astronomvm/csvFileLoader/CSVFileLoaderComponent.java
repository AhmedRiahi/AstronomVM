package com.astronomvm.csvFileLoader;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.AstronomObject;
import com.astronomvm.core.data.Column;
import com.astronomvm.core.data.DataType;
import com.astronomvm.core.data.Row;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CSVFileLoaderComponent extends BaseComponent {

    private static final String FILE_PATH_PARAMETER_NAME = "FILE_PATH";
    private static final String SEPARATOR_PARAMETER_NAME = "SEPARATOR";

    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("CSVFileLoaderComponent");

        ParameterMeta filePathParameterMeta = new ParameterMeta();
        filePathParameterMeta.setName(FILE_PATH_PARAMETER_NAME);
        filePathParameterMeta.setType(DataType.STRING);

        ParameterMeta separatorParameterMeta = new ParameterMeta();
        separatorParameterMeta.setName(SEPARATOR_PARAMETER_NAME);
        separatorParameterMeta.setType(DataType.STRING);

        componentMeta.addParameterMeta(separatorParameterMeta);
        return componentMeta;
    }

    public Optional<ResultSet> execute() throws ComponentException{
        ResultSet resultSet = new ResultSet();
        String filePath = this.inputParameters.getParameterByName(FILE_PATH_PARAMETER_NAME).getValue().toString();
        String separator = this.inputParameters.getParameterByName(SEPARATOR_PARAMETER_NAME).getValue().toString();
        try {
            Stream<String> stream = Files.lines(Paths.get(filePath));
            stream.forEachOrdered(line -> {
                Row row = new Row();
                String[] cols = line.split(separator);
                final AtomicInteger counter = new AtomicInteger();
                Arrays.stream(cols).forEachOrdered(col -> {
                    Column column = new Column();
                    column.setName("Col"+counter.incrementAndGet());
                    column.setValue(new AstronomObject(col));
                    row.addColumn(column);
                });
                resultSet.addRow(row);
            });
        } catch (IOException e) {
            throw new ComponentException(e.getMessage());
        }
        return Optional.of(resultSet);
    }


}
