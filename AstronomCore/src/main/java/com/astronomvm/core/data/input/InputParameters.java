package com.astronomvm.core.data.input;

import com.astronomvm.core.exception.ParameterNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputParameters implements Serializable{

    private List<InputParameter> inputParameters = new ArrayList<>();

    public void addParameter(InputParameter inputParameter){
        this.inputParameters.add(inputParameter);
    }


    public InputParameter getParameterByName(String name){
        return this.inputParameters.stream().filter(param -> param.getName().equals(name)).findAny().orElseThrow(ParameterNotFoundException::new);
    }
}
