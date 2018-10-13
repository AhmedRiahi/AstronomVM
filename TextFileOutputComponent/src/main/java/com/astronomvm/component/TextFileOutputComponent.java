package com.astronomvm.component;

import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.data.output.ResultSet;
import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.meta.component.ComponentMeta;
import com.astronomvm.core.model.meta.component.ComponentParameterMeta;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Slf4j
public class TextFileOutputComponent extends AstronomBaseComponent {


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

        ComponentParameterMeta filePathParameterMeta = new ComponentParameterMeta();
        filePathParameterMeta.setName(FILE_PATH_PARAMETER_NAME);
        filePathParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta separatorParameterMeta = new ComponentParameterMeta();
        separatorParameterMeta.setName(SEPARATOR_PARAMETER_NAME);
        separatorParameterMeta.setType(DataType.STRING);

        ComponentParameterMeta inputFlowNameParameterMeta = new ComponentParameterMeta();
        inputFlowNameParameterMeta.setName(INPUT_FLOW_NAME_PARAMETER_NAME);
        inputFlowNameParameterMeta.setType(DataType.INPUT_FLOW_NAME);

        componentMeta.addParameterMeta(filePathParameterMeta);
        componentMeta.addParameterMeta(separatorParameterMeta);
        componentMeta.addParameterMeta(inputFlowNameParameterMeta);
        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {
        String inputFlowParameterName = parametersValues.get(INPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.inputFlowResultSet = this.getInputResultFlow().getResultSet(inputFlowParameterName);
        this.filePath = parametersValues.get(FILE_PATH_PARAMETER_NAME).getString("value");
        this.separator = parametersValues.get(SEPARATOR_PARAMETER_NAME).getString("value");
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
