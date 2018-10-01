package com.astronomvm.textFileOutput;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class TextFileOutputComponent extends BaseComponent {


    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";
    private static final String FILE_PATH_PARAMETER_NAME = "FILE_PATH";
    private static final String SEPARATOR_PARAMETER_NAME = "SEPARATOR";

    private ResultSet inputFlowResultSet;
    private String filePath;
    private String separator;

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
    public void readInputs() {
        String inputFlowParameterName = this.inputParameters.getParameterByName(INPUT_FLOW_NAME_PARAMETER_NAME).getValue().toString();
        this.inputFlowResultSet = (ResultSet) this.inputParameters.getParameterByName(inputFlowParameterName).getValue().getUnderlying();
        this.filePath = this.inputParameters.getParameterByName(FILE_PATH_PARAMETER_NAME).getValue().toString();
        this.separator = this.inputParameters.getParameterByName(SEPARATOR_PARAMETER_NAME).getValue().toString();
    }

    @Override
    public ResultFlow execute() {
        File file = new File(filePath);

        try {
            Files.deleteIfExists(file.toPath());

            if(file.createNewFile()){
                Path path = Paths.get(this.filePath);

                try (BufferedWriter writer = Files.newBufferedWriter(path))
                {
                    this.inputFlowResultSet.getRows().stream().forEach(row -> {
                        row.getColumns().stream().forEach(column -> {
                            try {
                                writer.write(column.getValue().getUnderlying()+this.separator);
                            } catch (IOException e) {
                                log.error(e.getMessage(),e);
                            }
                        });
                        try {
                            writer.newLine();
                        } catch (IOException e) {
                            log.error(e.getMessage(),e);
                        }
                    });
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }


        return null;
    }
}
