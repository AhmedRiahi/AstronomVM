package com.astronomvm.core.meta;

import lombok.Data;

import java.util.Set;

@Data
public class ComponentMeta {

    private String name;
    private Set<ParameterMeta> parameterMetas;
}
