package com.astronomvm.core.meta;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AstronomMetaFlow {


    private List<StepMeta> stepMetaList;
    private List<Transition> transitions;



    public List<StepMeta> getStepMetaList(){
        List<StepMeta> copy = new ArrayList<>();
        copy.addAll(this.stepMetaList);
        return copy;
    }

    public List<Transition> getTransitions(){
        List<Transition> copy = new ArrayList<>();
        copy.addAll(transitions);
        return copy;
    }
}
