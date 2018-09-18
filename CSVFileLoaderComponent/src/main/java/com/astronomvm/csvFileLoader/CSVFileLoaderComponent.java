package com.astronomvm.csvFileLoader;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.data.DataType;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;

import java.util.Optional;

public class CSVFileLoaderComponent extends BaseComponent {

    private static final String FILE_PATH_PARAMETER_NAME = "FILE_PATH";

    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("CSVFileLoaderComponent");
        ParameterMeta filePathParameterMeta = new ParameterMeta();
        filePathParameterMeta.setName(FILE_PATH_PARAMETER_NAME);
        filePathParameterMeta.setType(DataType.STRING);
        componentMeta.addParameterMeta(filePathParameterMeta);
        return componentMeta;
    }

    public Optional<ResultSet> execute() {
        String filePath = this.inputParameters.getParameterByName(FILE_PATH_PARAMETER_NAME).getValue().toString();
        return Optional.of(null);
    }


}
