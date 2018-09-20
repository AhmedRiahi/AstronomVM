package com.astronomvm.designer.payload;

import lombok.Data;

import java.util.List;

@Data
public class AstronomOperation {

    private Integer id;
    private String name;

    private List<AstronomStep> steps;
    private List<AstronomTransition> transitions;


}
