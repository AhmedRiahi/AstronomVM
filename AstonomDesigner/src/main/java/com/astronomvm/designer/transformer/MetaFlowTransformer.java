package com.astronomvm.designer.transformer;

import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.designer.persistence.entity.process.MetaFlowEntity;

import java.util.stream.Collectors;

public class MetaFlowTransformer {


    public static MetaFlow fromEntity(MetaFlowEntity metaFlowEntity){
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.setStepMetaList(metaFlowEntity.getSteps().stream().map(StepMetaTransformer::fromEntity).collect(Collectors.toList()));
        metaFlow.setTransitions(metaFlowEntity.getTransitions().stream().map(TransitionMetaTransformer::fromEntity).collect(Collectors.toList()));
        return metaFlow;
    }
}
