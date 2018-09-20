package com.astronomvm.designer.payload;


import lombok.Data;

@Data
public class ComponentParameterDefValue {

    private Integer id;
    private ComponentParameterDef componentParameterDef;
    private String value;
}
