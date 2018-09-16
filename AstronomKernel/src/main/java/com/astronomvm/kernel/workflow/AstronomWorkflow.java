package com.astronomvm.kernel.workflow;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.core.meta.AstronomMetaFlow;

import java.util.HashMap;
import java.util.Map;

public class AstronomWorkflow extends AstronomMetaFlow {

    private Map<String,BaseComponent> baseComponentMap = new HashMap<>();

    public void addComponent(String name,BaseComponent baseComponent){
        this.baseComponentMap.put(name,baseComponent);
    }

    public BaseComponent getComponentByName(String name){
        return this.baseComponentMap.get(name);
    }
}
