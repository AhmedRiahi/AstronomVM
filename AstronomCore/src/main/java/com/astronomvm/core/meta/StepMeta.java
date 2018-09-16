package com.astronomvm.core.meta;

import com.astronomvm.core.data.input.InputParameters;
import lombok.Data;

@Data
public class StepMeta {

    private ComponentMeta componentMeta;
    private InputParameters inputParameters;
}
