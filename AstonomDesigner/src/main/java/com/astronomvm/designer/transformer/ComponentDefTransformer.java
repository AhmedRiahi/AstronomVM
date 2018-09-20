package com.astronomvm.designer.transformer;


import com.astronomvm.designer.payload.AstronomComponentDefinition;
import com.astronomvm.designer.payload.ComponentParameterDef;
import com.astronomvm.designer.persistence.entity.component.ComponentDefinitionEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

public class ComponentDefTransformer {


    public static AstronomComponentDefinition convertComponentDefEntity(ComponentDefinitionEntity componentDefinitionEntity){
        AstronomComponentDefinition componentDefinition = new AstronomComponentDefinition();
        BeanUtils.copyProperties(componentDefinitionEntity,componentDefinition);

        componentDefinition.setParametersDefs(new ArrayList<>());
        componentDefinitionEntity.getParametersDefs().stream().forEach(parameterDefEntity ->{
            ComponentParameterDef componentParameterDef = new ComponentParameterDef();
            BeanUtils.copyProperties(parameterDefEntity,componentParameterDef);
            componentDefinition.getParametersDefs().add(componentParameterDef);

        });
        return componentDefinition;
    }
}
