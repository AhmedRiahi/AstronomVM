package com.astronomvm.designer.transformer;

import com.astronomvm.core.model.meta.flow.StepMeta;
import com.astronomvm.designer.persistence.entity.workflow.StepMetaEntity;

public class StepMetaTransformer {

    private StepMetaTransformer(){}

    public static synchronized StepMeta fromEntity(StepMetaEntity stepMetaEntity){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName(stepMetaEntity.getName());
        stepMeta.setComponentName(stepMetaEntity.getComponentName());
        stepMeta.setParametersValues(stepMetaEntity.getParametersValues());
        return stepMeta;
    }
}
