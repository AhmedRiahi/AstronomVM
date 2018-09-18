package com.astronomvm.kernel.spi;

import com.astronomvm.core.meta.ComponentMeta;

import java.util.HashMap;
import java.util.Map;


public class ComponentsRegistryBoard {

    private static final ComponentsRegistryBoard instance = new ComponentsRegistryBoard();

    private Map<ComponentMeta,Class> componentMap = new HashMap<>();

    private ComponentsRegistryBoard(){}

    public static ComponentsRegistryBoard getInstance(){
        return ComponentsRegistryBoard.instance;
    }

    public void registerComponent(ComponentMeta componentMeta,Class clazz){
        this.componentMap.put(componentMeta,clazz);
    }

    public Class getComponentClass(String name){
        return this.componentMap.values().stream().filter(componentMeta -> componentMeta.getName().equals(name)).findAny().get();
    }

}
