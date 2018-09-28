package com.astronomvm.designer.transformer;

import com.astronomvm.core.data.input.InputParameter;
import com.astronomvm.core.data.row.AstronomObject;
import com.astronomvm.designer.persistence.entity.component.InputParameterEntity;

public class InputParameterTransformer {


    public static InputParameter fromEntity(InputParameterEntity inputParameterEntity){
        InputParameter inputParameter = new InputParameter();
        inputParameter.setName(inputParameterEntity.getName());
        inputParameter.setValue(new AstronomObject(inputParameterEntity.getValue()));
        return inputParameter;
    }
}
