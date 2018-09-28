package com.astronomvm.designer.transformer;

import com.astronomvm.core.meta.TransitionMeta;
import com.astronomvm.designer.persistence.entity.process.TransitionMetaEntity;

public class TransitionMetaTransformer {


    public static TransitionMeta fromEntity(TransitionMetaEntity transitionMetaEntity){
        TransitionMeta transitionMeta = new TransitionMeta();

        return transitionMeta;
    }
}
