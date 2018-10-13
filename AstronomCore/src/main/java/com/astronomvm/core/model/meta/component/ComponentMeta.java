package com.astronomvm.core.model.meta.component;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ComponentMeta {

    private String name;
    private Set<ComponentParameterMeta> parameterMetas = new HashSet<>();

    public void addParameterMeta(ComponentParameterMeta parameterMeta){
        this.parameterMetas.add(parameterMeta);
    }
}
