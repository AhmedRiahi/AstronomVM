package com.astronomvm.textFileOutput;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.astonomType.DataType;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileOutputComponent extends BaseComponent {


    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";
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

        ParameterMeta inputFlowNameParameterMeta = new ParameterMeta();
        inputFlowNameParameterMeta.setName(INPUT_FLOW_NAME_PARAMETER_NAME);
        inputFlowNameParameterMeta.setType(DataType.INPUT_FLOW_NAME);

        componentMeta.addParameterMeta(filePathParameterMeta);
        componentMeta.addParameterMeta(separatorParameterMeta);
        componentMeta.addParameterMeta(inputFlowNameParameterMeta);
        return componentMeta;
    }

    @Override
    public ResultFlow execute() throws ComponentException {
        String inputFlowParameterName = this.inputParameters.getParameterByName(INPUT_FLOW_NAME_PARAMETER_NAME).getValue().toString();
        ResultSet inputFlowResultSet = (ResultSet) this.inputParameters.getParameterByName(inputFlowParameterName).getValue().getUnderlying();
        String filePath = this.inputParameters.getParameterByName(FILE_PATH_PARAMETER_NAME).getValue().toString();
        String separator = this.inputParameters.getParameterByName(SEPARATOR_PARAMETER_NAME).getValue().toString();

        File file = new File(filePath);

        try {
            if(file.exists()){
                file.delete();
            }
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
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
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
