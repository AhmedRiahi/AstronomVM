package com.astronomvm.core.meta;

import lombok.Data;

import java.util.List;

@Data
public class ComponentMeta {

    private String name;
    private List<ParameterMeta> parameterMetas;
}
