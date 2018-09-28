package com.astronomvm.core.data.input;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class InputParameters implements Serializable{

    private List<InputParameter> inputParameters = new ArrayList<>();

    public void addParameter(InputParameter inputParameter){
        this.inputParameters.add(inputParameter);
    }


    public InputParameter getParameterByName(String name){
        return this.inputParameters.stream().filter(param -> param.getName().equals(name)).findFirst().get();
    }
}
