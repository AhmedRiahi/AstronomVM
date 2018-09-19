package com.astronomvm.core.data.input;

import java.util.ArrayList;
import java.util.List;

public class InputParameters {

    private List<InputParameter> inputParameters = new ArrayList<>();

    public void addParameter(InputParameter inputParameter){
        this.inputParameters.add(inputParameter);
    }


    public InputParameter getParameterByName(String name){
        return this.inputParameters.stream().filter(param -> param.getName().equals(name)).findFirst().get();
    }
}
