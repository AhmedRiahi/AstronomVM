package com.astronomvm.kernel.spi;

import com.astronomvm.core.model.meta.ComponentMeta;
import com.astronomvm.kernel.exception.ComponentClassNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
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
        return this.componentMap.entrySet().stream().filter(entry -> entry.getKey().getName().equals(name)).findAny().orElseThrow(() -> new ComponentClassNotFoundException(name)).getValue();
    }

    public Set<ComponentMeta> getAllRegisteredComponents(){
        return new HashSet<>(this.componentMap.keySet());
    }

}
