package com.astronomvm.core.meta;

import com.astronomvm.core.exception.StepMetaNotFoundException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MetaFlow {


    private List<StepMeta> stepMetaList = new ArrayList<>();
    private List<TransitionMeta> transitions = new ArrayList<>();


    public void addStepMeta(StepMeta stepMeta){
        this.stepMetaList.add(stepMeta);
    }

    public void addTransition(TransitionMeta transition){
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

    public List<TransitionMeta> getTransitions(){
        List<TransitionMeta> copy = new ArrayList<>();
        copy.addAll(transitions);
        return copy;
    }

    public StepMeta getStepMetaByName(String name){
        return this.stepMetaList.stream().filter(stepMeta -> stepMeta.getName().equals(name)).findAny().orElseThrow(StepMetaNotFoundException::new);
    }
}
