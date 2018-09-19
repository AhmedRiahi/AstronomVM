package com.astronomvm.textFileOutput;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.row.DataType;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileOutputComponent extends BaseComponent {


    private static final String INPUT_FLOW_PARAMETER_NAME = "INPUT_FLOW";
    private static final String FILE_PATH_PARAMETER_NAME = "FILE_PATH";
    private static final String SEPARATOR_PARAMETER_NAME = "SEPARATOR";

    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("TEXT_FILE_OUTPUT");

        ParameterMeta filePathParameterMeta = new ParameterMeta();
        filePathParameterMeta.setName(FILE_PATH_PARAMETER_NAME);
        filePathParameterMeta.setType(DataType.STRING);

        ParameterMeta separatorParameterMeta = new ParameterMeta();
        separatorParameterMeta.setName(SEPARATOR_PARAMETER_NAME);
        separatorParameterMeta.setType(DataType.STRING);

        componentMeta.addParameterMeta(filePathParameterMeta);
        componentMeta.addParameterMeta(separatorParameterMeta);
        return componentMeta;
    }

    @Override
    public ResultFlow execute() throws ComponentException {

        ResultSet inputFlowResultSet = (ResultSet) this.inputParameters.getParameterByName(INPUT_FLOW_PARAMETER_NAME).getValue().getUnderlying();
        String filePath = this.inputParameters.getParameterByName(FILE_PATH_PARAMETER_NAME).getValue().toString();
        String separator = this.inputParameters.getParameterByName(SEPARATOR_PARAMETER_NAME).getValue().toString();

        File file = new File(filePath);

        try {
            if(file.createNewFile()){
                Path path = Paths.get(filePath);

                try (BufferedWriter writer = Files.newBufferedWriter(path))
                {
                    inputFlowResultSet.getRows().stream().forEach(row -> {
                        row.getColumns().stream().forEach(column -> {
                            try {
                                writer.write(column.getValue().getUnderlying()+separator);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        try {
                            writer.write("\n");
                        } catch (IOException e) {
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
