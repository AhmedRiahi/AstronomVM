package com.astronomvm.functional_repository.transformer;

import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.meta.functional.FunctionalAttributeMeta;
import com.astronomvm.functional_repository.persistence.entity.FunctionalAttributeEntity;

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
