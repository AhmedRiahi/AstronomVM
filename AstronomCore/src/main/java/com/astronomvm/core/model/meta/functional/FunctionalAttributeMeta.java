package com.astronomvm.core.model.meta.functional;

import com.astronomvm.core.model.data.type.DataType;
import lombok.Data;

@Data
public class FunctionalAttributeMeta {
    private String name;
    private DataType type;
}
