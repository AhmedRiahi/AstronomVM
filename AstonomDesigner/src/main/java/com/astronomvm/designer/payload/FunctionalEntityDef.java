package com.astronomvm.designer.payload;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FunctionalEntityDef {

    private String name;
    private List<FunctionalEntityFieldDef> fields;

    public List<FunctionalEntityFieldDef> getPrimaryKeyFields(){
        return this.fields.stream().filter(field -> field.getIsPrimaryKey()).collect(Collectors.toList());
    }
}
