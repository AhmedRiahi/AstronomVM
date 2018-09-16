package com.astronomvm.core.meta;

import lombok.Data;

import java.util.List;

@Data
public class AstronomMetaFlow {


    private List<StepMeta> stepMetaList;
    private List<Transition> transitions;
}
