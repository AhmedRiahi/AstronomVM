package com.astronomvm.kernel.spi;

import com.astronomvm.core.model.meta.component.ComponentMeta;
import com.astronomvm.kernel.exception.ComponentClassNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ComponentsRegistryBoard {

    private static final ComponentsRegistryBoard instance = new ComponentsRegistryBoard();

    private Map<ComponentMeta,Class> componentMap = new ConcurrentHashMap<>();
    private Map<String,Class> indexedComponentClasses = new ConcurrentHashMap<>();

    private ComponentsRegistryBoard(){}

    public static ComponentsRegistryBoard getInstance(){
        return ComponentsRegistryBoard.instance;
    }

    public synchronized void registerComponent(ComponentMeta componentMeta,Class clazz){
        this.componentMap.put(componentMeta,clazz);
        this.indexedComponentClasses.put(componentMeta.getName(),clazz);
    }

    public Class getComponentClass(String name){
        return Optional.ofNullable(this.indexedComponentClasses.get(name)).orElseThrow(() -> new ComponentClassNotFoundException(name));
    }

    public Set<ComponentMeta> getAllRegisteredComponents(){
        return new HashSet<>(this.componentMap.keySet());
    }

}
