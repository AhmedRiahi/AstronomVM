package com.astronomvm.core.meta.functional;

import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.exception.FunctionalAttributeNotFoundException;
import lombok.Data;


import java.util.List;

@Data
public class FunctionalModelMeta {

    private String name;
    private List<FunctionalAttributeMeta> attributes ;

    public FunctionalAttributeMeta getAttributeByName(String attributeName){
        return this.attributes.stream().filter(attribute -> attribute.getName().equals(attributeName)).findAny().orElseThrow(()->new FunctionalAttributeNotFoundException(attributeName));
    }

    public DataType getAttributeType(String attributeName){
        return this.getAttributeByName(attributeName).getType();
    }
}
