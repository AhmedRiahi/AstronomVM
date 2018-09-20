package com.astronomvm.designer.payload;

import lombok.Data;

import java.util.List;

@Data
public class AstronomProject {

    private Integer id;
    private String name;
    private List<AstronomOperation> operations;
}
