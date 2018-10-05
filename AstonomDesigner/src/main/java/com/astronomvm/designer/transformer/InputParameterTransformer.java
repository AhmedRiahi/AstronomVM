package com.astronomvm.designer.transformer;

import com.astronomvm.core.data.input.InputParameterMeta;
import com.astronomvm.core.data.row.AstronomObject;
import com.astronomvm.designer.persistence.entity.component.InputParameterEntity;

public class InputParameterTransformer {

    private InputParameterTransformer(){}

    public static InputParameterMeta fromEntity(InputParameterEntity inputParameterEntity){
        InputParameterMeta inputParameter = new InputParameterMeta();
        inputParameter.setName(inputParameterEntity.getName());
        inputParameter.setValue(new AstronomObject(inputParameterEntity.getValue()));
        return inputParameter;
    }
}
