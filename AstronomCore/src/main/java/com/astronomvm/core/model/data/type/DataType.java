package com.astronomvm.core.model.data.type;

import com.astronomvm.core.exception.DataTypeNotFoundException;

import java.util.Arrays;

public enum DataType {
    STRING,
    NUMBER,
    INPUT_FLOW_NAME,
    FUNCTIONAL_MODEL,
    FUNCTIONAL_MODEL_REPOSITORY_NAME,
    DATABASE_CONNECTION_PROPERTIES,
    OBJECT,
    UNKNOWN;


    public static DataType findByName(String name){
        return Arrays.stream(DataType.values()).filter(type -> type.name().equals(name)).findAny().orElseThrow(() -> new DataTypeNotFoundException(name));
    }
}
