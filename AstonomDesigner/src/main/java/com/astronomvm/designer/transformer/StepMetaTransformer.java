package com.astronomvm.designer.transformer;

import com.astronomvm.core.data.input.InputParameters;
import com.astronomvm.core.meta.StepMeta;
import com.astronomvm.designer.persistence.entity.process.StepMetaEntity;

import java.util.stream.Collectors;

public class StepMetaTransformer {

    private StepMetaTransformer(){}

    public static StepMeta fromEntity(StepMetaEntity stepMetaEntity){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName(stepMetaEntity.getName());
        stepMeta.setComponentName(stepMetaEntity.getComponentName());
        stepMeta.setInputParameters(new InputParameters(stepMetaEntity.getInputParameters().stream().map(InputParameterTransformer::fromEntity).collect(Collectors.toList())));
        return stepMeta;
    }
}
