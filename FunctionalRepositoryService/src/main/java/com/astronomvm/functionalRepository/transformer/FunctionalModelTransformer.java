package com.astronomvm.functionalRepository.transformer;

import com.astronomvm.core.meta.functional.FunctionalModelMeta;
import com.astronomvm.functionalRepository.persistence.entity.FunctionalModelEntity;

import java.util.stream.Collectors;

public class FunctionalModelTransformer {

    private FunctionalModelTransformer(){}

    public synchronized static FunctionalModelMeta fromEntity(FunctionalModelEntity entity){
        FunctionalModelMeta functionalModelMeta = new FunctionalModelMeta();
        functionalModelMeta.setName(entity.getName());
        functionalModelMeta.setAttributes(entity.getFunctionalAttributes().stream().map(FunctionalAttributeTransformer::fromEntity).collect(Collectors.toList()));
        return functionalModelMeta;
    }


    public synchronized static FunctionalModelEntity fromPayload(FunctionalModelMeta payload){
        FunctionalModelEntity functionalModelEntity = new FunctionalModelEntity();
        functionalModelEntity.setName(payload.getName());
        functionalModelEntity.setFunctionalAttributes(payload.getAttributes().stream().map(FunctionalAttributeTransformer::fromPayload).collect(Collectors.toList()));
        return functionalModelEntity;
    }
}
