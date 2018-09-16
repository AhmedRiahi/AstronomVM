package com.astronomvm.kernel.factory;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.kernel.component.ComponentsRegistryBoard;

public class ComponentFactory {


    private static final ComponentFactory instance = new ComponentFactory();

    public static ComponentFactory getInstance(){
        return ComponentFactory.instance;
    }


    public static BaseComponent buildComponent(String name) throws IllegalAccessException, InstantiationException {
        Class componentClass = ComponentsRegistryBoard.getInstance().getComponentClass(name);
        return (BaseComponent)componentClass.newInstance();
    }

}
