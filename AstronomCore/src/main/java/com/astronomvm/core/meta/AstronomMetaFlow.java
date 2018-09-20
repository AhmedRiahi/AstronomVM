package com.astronomvm.core.meta;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AstronomMetaFlow {


    private List<StepMeta> stepMetaList = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();


    public void addStepMeta(StepMeta stepMeta){
        this.stepMetaList.add(stepMeta);
    }

    public void addTransition(Transition transition){
        this.transitions.add(transition);
    }

    public List<StepMeta> getSourceSteps(StepMeta stepMeta){
        return this.transitions.stream().filter(transition -> transition.getTarget().equals(stepMeta)).map(transition -> transition.getSource()).collect(Collectors.toList());
    }

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
