package com.astronomvm.csvFileLoader;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.meta.ComponentMeta;

public class CSVFileLoaderComponent extends BaseComponent {


    public ComponentMeta getComponentMeta() {
        return null;
    }

    public void execute() {
        String filePath = this.inputParameters.getParameterByName("FILE_PATH").getValue().toString();
    }

}
