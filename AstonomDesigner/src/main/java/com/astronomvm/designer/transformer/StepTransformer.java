package com.astronomvm.designer.transformer;


import com.astronomvm.designer.payload.AstronomStep;
import com.astronomvm.designer.payload.ComponentParameterDef;
import com.astronomvm.designer.payload.ComponentParameterDefValue;
import com.astronomvm.designer.persistence.entity.process.StepEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

public class StepTransformer {


    public static AstronomStep convertStepEntity(StepEntity stepEntity){
        AstronomStep step = new AstronomStep();
        BeanUtils.copyProperties(stepEntity,step);
        step.setComponentDefinition(ComponentDefTransformer.convertComponentDefEntity(stepEntity.getComponentDefinition()));
        step.setParametersValues(new ArrayList<>());
        stepEntity.getParametersValues().stream().forEach(parameterValueEntity ->{
            ComponentParameterDefValue componentParameterDefValue = new ComponentParameterDefValue();
            BeanUtils.copyProperties(parameterValueEntity,componentParameterDefValue);
            ComponentParameterDef componentParameterDef = new ComponentParameterDef();
            BeanUtils.copyProperties(parameterValueEntity.getComponentParameterDef(),componentParameterDef);
            componentParameterDefValue.setComponentParameterDef(componentParameterDef);
            step.getParametersValues().add(componentParameterDefValue);
        });
        return step;
    }
}
