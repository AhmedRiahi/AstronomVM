package com.astronomvm.core.model.meta.flow;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepMeta {

    private String name;
    private String componentName;
    private String parametersValues;

}
