package com.astronomvm.designer.transformer;

import com.astronomvm.core.meta.StepMeta;
import com.astronomvm.designer.persistence.entity.workflow.StepMetaEntity;

public class StepMetaTransformer {

    private StepMetaTransformer(){}

    public static StepMeta fromEntity(StepMetaEntity stepMetaEntity){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName(stepMetaEntity.getName());
        stepMeta.setComponentName(stepMetaEntity.getComponentName());
        stepMeta.setParametersValues(stepMetaEntity.getParametersValues());
        return stepMeta;
    }
}
