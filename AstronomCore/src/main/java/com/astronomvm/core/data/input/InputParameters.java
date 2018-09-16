package com.astronomvm.core.data.input;

import java.util.List;

public class InputParameters {

    private List<InputParameter> inputParameters;


    public InputParameter getParameterByName(String name){
        return this.inputParameters.stream().filter(param -> param.getName().equals(name)).findFirst().get();
    }
}
