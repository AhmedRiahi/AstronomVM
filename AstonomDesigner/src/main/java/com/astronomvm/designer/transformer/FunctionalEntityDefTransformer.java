package com.astronomvm.designer.transformer;


import com.astronomvm.designer.payload.FunctionalEntityDef;
import com.astronomvm.designer.payload.FunctionalEntityFieldDef;
import com.astronomvm.designer.persistence.entity.functionalEntity.FunctionalEntityDefEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

public class FunctionalEntityDefTransformer {


    public static FunctionalEntityDef convertEntity(FunctionalEntityDefEntity functionalEntityDefEntity){
        FunctionalEntityDef functionalEntityDef = new FunctionalEntityDef();
        BeanUtils.copyProperties(functionalEntityDefEntity,functionalEntityDef);
        functionalEntityDef.setFields(new ArrayList<>());
        functionalEntityDefEntity.getFields().stream().forEach(entityField ->{
            FunctionalEntityFieldDef functionalEntityFieldDef = new FunctionalEntityFieldDef();
            BeanUtils.copyProperties(entityField,functionalEntityFieldDef);
            functionalEntityDef.getFields().add(functionalEntityFieldDef);
        });
        return functionalEntityDef;
    }
}
