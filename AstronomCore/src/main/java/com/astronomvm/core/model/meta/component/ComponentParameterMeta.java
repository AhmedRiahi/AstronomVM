package com.astronomvm.core.model.meta.component;

import com.astronomvm.core.model.data.type.DataType;
import lombok.Data;

@Data
public class ComponentParameterMeta {

    private String name;
    private DataType type;
    private boolean userInput = true;
}
