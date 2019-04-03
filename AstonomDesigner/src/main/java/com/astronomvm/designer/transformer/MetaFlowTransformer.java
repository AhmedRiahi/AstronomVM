package com.astronomvm.designer.transformer;

import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.core.model.meta.flow.StepMeta;
import com.astronomvm.core.model.meta.flow.TransitionMeta;
import com.astronomvm.designer.persistence.entity.workflow.MetaFlowEntity;

import java.util.stream.Collectors;

public class MetaFlowTransformer {

    private MetaFlowTransformer(){}


    public static synchronized MetaFlow fromEntity(MetaFlowEntity metaFlowEntity){
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.setName(metaFlowEntity.getName());
        metaFlow.setStepMetaList(metaFlowEntity.getSteps().stream().map(StepMetaTransformer::fromEntity).collect(Collectors.toList()));
        metaFlowEntity.getTransitions().stream().forEach(transitionMetaEntity -> {
            StepMeta fromStep = metaFlow.getStepMetaByName(transitionMetaEntity.getFromStep().getName());
            StepMeta toStep =  metaFlow.getStepMetaByName(transitionMetaEntity.getToStep().getName());
            TransitionMeta transitionMeta = new TransitionMeta();
            transitionMeta.setSource(fromStep);
            transitionMeta.setTarget(toStep);
            transitionMeta.setSourceFlowName(transitionMetaEntity.getSourceFlowName());
            transitionMeta.setTargetFlowName(transitionMetaEntity.getTargetFlowName());
            metaFlow.addTransition(transitionMeta);
        } );
        return metaFlow;
    }
}
