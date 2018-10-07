package com.astronomvm.kernel.factory;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.kernel.spi.ComponentsRegistryBoard;

public class ComponentFactory {


    private static final ComponentFactory instance = new ComponentFactory();

    public static ComponentFactory getInstance(){
        return ComponentFactory.instance;
    }

    private ComponentFactory(){}

    public AstronomBaseComponent buildComponent(String name) throws IllegalAccessException, InstantiationException {
        Class componentClass = ComponentsRegistryBoard.getInstance().getComponentClass(name);
        return (AstronomBaseComponent)componentClass.newInstance();
    }

}
