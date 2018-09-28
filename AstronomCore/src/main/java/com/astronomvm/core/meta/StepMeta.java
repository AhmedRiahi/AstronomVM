package com.astronomvm.core.meta;

import com.astronomvm.core.data.input.InputParameters;
import lombok.Data;

@Data
public class StepMeta {

    private String name;
    private String componentName;
    private InputParameters inputParameters = new InputParameters();

}
