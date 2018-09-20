package com.astronomvm.designer.payload;


import lombok.Data;

@Data
public class FunctionalEntityFieldDef {

    private String name;
    private String type;
    private Boolean isPrimaryKey;
}
