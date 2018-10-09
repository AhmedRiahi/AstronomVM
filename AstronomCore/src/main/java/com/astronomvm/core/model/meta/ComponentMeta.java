package com.astronomvm.core.model.meta;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ComponentMeta {

    private String name;
    private Set<ParameterMeta> parameterMetas = new HashSet<>();

    public void addParameterMeta(ParameterMeta parameterMeta){
        this.parameterMetas.add(parameterMeta);
    }
}
