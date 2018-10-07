package com.astronomvm.functionalRepository.transformer;

import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.meta.functional.FunctionalAttributeMeta;
import com.astronomvm.functionalRepository.persistence.entity.FunctionalAttributeEntity;

public class FunctionalAttributeTransformer {

    private FunctionalAttributeTransformer(){}


    public static synchronized FunctionalAttributeMeta fromEntity(FunctionalAttributeEntity entity){
        FunctionalAttributeMeta functionalAttributeMeta = new FunctionalAttributeMeta();
        functionalAttributeMeta.setName(entity.getName());
        functionalAttributeMeta.setType(DataType.findByName(entity.getType()));
        return functionalAttributeMeta;
    }


    public static synchronized FunctionalAttributeEntity fromPayload(FunctionalAttributeMeta payload){
        FunctionalAttributeEntity functionalAttributeEntity = new FunctionalAttributeEntity();
        functionalAttributeEntity.setName(payload.getName());
        functionalAttributeEntity.setType(payload.getType().name());
        return functionalAttributeEntity;
    }
}
