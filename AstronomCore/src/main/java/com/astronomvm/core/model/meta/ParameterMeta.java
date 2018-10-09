package com.astronomvm.core.model.meta;

import com.astronomvm.core.model.data.type.DataType;
import lombok.Data;

@Data
public class ParameterMeta {

    private String name;
    private DataType type;
    private boolean userInput = true;
}
