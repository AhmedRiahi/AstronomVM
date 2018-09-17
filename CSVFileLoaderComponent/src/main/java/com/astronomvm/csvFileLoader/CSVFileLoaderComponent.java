package com.astronomvm.csvFileLoader;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.meta.ComponentMeta;

import java.util.Optional;

public class CSVFileLoaderComponent extends BaseComponent {


    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("CSVFileLoaderComponent");
        return componentMeta;
    }

    public Optional<ResultSet> execute() {
        String filePath = this.inputParameters.getParameterByName("FILE_PATH").getValue().toString();
        return Optional.of(null);
    }


}
