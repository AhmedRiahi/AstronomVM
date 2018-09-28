package com.astronomvm.designer.transformer;

import com.astronomvm.core.meta.StepMeta;
import com.astronomvm.designer.persistence.entity.process.StepMetaEntity;

public class StepMetaTransformer {

    public static StepMeta fromEntity(StepMetaEntity stepMetaEntity){
        StepMeta stepMeta = new StepMeta();

        return stepMeta;
    }
}
