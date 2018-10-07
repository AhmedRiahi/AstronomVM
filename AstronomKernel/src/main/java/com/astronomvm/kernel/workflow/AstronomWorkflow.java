package com.astronomvm.kernel.workflow;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.core.meta.MetaFlow;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AstronomWorkflow {


    private MetaFlow metaFlow;
    private Map<String,AstronomBaseComponent> baseComponentMap = new HashMap<>();

    public AstronomWorkflow(MetaFlow metaFlow){
        this.metaFlow = metaFlow;
    }

    public void addComponent(String name,AstronomBaseComponent baseComponent){
        this.baseComponentMap.put(name,baseComponent);
    }

    public AstronomBaseComponent getComponentByName(String name){
        return this.baseComponentMap.get(name);
    }
}
