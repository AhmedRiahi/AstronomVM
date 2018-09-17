package com.astronomvm.kernel.spi;

import java.util.HashMap;
import java.util.Map;


public class ComponentsRegistryBoard {

    private static final ComponentsRegistryBoard instance = new ComponentsRegistryBoard();

    private Map<String,Class> componentMap = new HashMap<>();

    private ComponentsRegistryBoard(){}

    public static ComponentsRegistryBoard getInstance(){
        return ComponentsRegistryBoard.instance;
    }

    public void registerComponent(String name,Class clazz){
        this.componentMap.put(name,clazz);
    }

    public Class getComponentClass(String name){
        return this.componentMap.get(name);
    }

}
