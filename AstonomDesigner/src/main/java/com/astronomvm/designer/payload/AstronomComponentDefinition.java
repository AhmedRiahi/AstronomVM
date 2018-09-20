package com.astronomvm.designer.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class AstronomComponentDefinition {

    private String name;
    private List<ComponentParameterDef> parametersDefs;
}
