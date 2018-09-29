package com.astronomvm.designer.transformer;

import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.core.meta.StepMeta;
import com.astronomvm.core.meta.TransitionMeta;
import com.astronomvm.designer.persistence.entity.process.MetaFlowEntity;

import java.util.stream.Collectors;

public class MetaFlowTransformer {


    public static MetaFlow fromEntity(MetaFlowEntity metaFlowEntity){
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.setStepMetaList(metaFlowEntity.getSteps().stream().map(StepMetaTransformer::fromEntity).collect(Collectors.toList()));
        metaFlowEntity.getTransitions().stream().forEach(transitionMetaEntity -> {
            StepMeta fromStep = metaFlow.getStepMetaByName(transitionMetaEntity.getFromStep().getName());
            StepMeta toStep =  metaFlow.getStepMetaByName(transitionMetaEntity.getToStep().getName());
            TransitionMeta transitionMeta = new TransitionMeta();
            transitionMeta.setSource(fromStep);
            transitionMeta.setTarget(toStep);
            metaFlow.addTransition(transitionMeta);
        } );
        return metaFlow;
    }
}
