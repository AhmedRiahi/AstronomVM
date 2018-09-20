package com.astronomvm.designer.payload;


import lombok.Data;

import java.util.List;

@Data
public class AstronomStep {

    private Integer id;
    private String name;
    private AstronomComponentDefinition componentDefinition;
    private List<ComponentParameterDefValue> parametersValues;

    public ComponentParameterDefValue lookupParameterValue(final ComponentParameterDef parameterDef){
        return this.parametersValues.stream().filter(parameterValue -> parameterValue.getComponentParameterDef().getId().equals(parameterDef.getId())).findFirst().get();
    }

}
