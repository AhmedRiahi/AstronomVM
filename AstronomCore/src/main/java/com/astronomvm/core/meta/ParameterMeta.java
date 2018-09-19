package com.astronomvm.core.meta;

import com.astronomvm.core.data.row.DataType;
import lombok.Data;

@Data
public class ParameterMeta {

    private String name;
    private DataType type;
}
