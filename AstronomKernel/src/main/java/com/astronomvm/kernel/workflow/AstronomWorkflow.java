package com.astronomvm.kernel.workflow;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.meta.AstronomMetaFlow;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AstronomWorkflow {


    private AstronomMetaFlow astronomMetaFlow;
    private Map<String,BaseComponent> baseComponentMap = new HashMap<>();

    public AstronomWorkflow(AstronomMetaFlow astronomMetaFlow){
        this.astronomMetaFlow = astronomMetaFlow;
    }

    public void addComponent(String name,BaseComponent baseComponent){
        this.baseComponentMap.put(name,baseComponent);
    }

    public BaseComponent getComponentByName(String name){
        return this.baseComponentMap.get(name);
    }
}
