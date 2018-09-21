package com.astronomvm.kernel.spi;

import com.astronomvm.core.meta.ComponentMeta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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
        return this.componentMap.entrySet().stream().filter(entry -> entry.getKey().getName().equals(name)).findAny().get().getValue();
    }

    public Set<ComponentMeta> getAllRegisteredComponents(){
        return new HashSet<>(this.componentMap.keySet());
    }

}
