package com.astronomvm.core.model.meta.component;

import lombok.Data;

import java.util.*;

@Data
public class ComponentMeta {

    private String name;
    private List<ComponentParameterMeta> parameterMetas = new ArrayList<>();

    public void addParameterMeta(ComponentParameterMeta parameterMeta){
        this.parameterMetas.add(parameterMeta);
    }
}
