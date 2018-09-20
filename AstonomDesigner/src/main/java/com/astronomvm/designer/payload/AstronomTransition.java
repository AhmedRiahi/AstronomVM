package com.astronomvm.designer.payload;

import lombok.Data;


@Data
public class AstronomTransition {

    private Integer id;
    private AstronomStep fromStep;
    private AstronomStep toStep;
    private Boolean isErrorTransition;

}
