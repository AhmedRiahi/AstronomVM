package com.astronomvm.kernel.spi;

import com.astronomvm.component.BaseComponent;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ComponentsLoader {

    private static final ComponentsLoader instance = new ComponentsLoader();

    private ComponentsLoader(){}

    public static ComponentsLoader getInstance(){
        return ComponentsLoader.instance;
    }

    public void loadComponents(){
        ServiceLoader<BaseComponent> loaders = ServiceLoader.load(BaseComponent.class);
        Iterator<BaseComponent> iterator = loaders.iterator();
        while(iterator.hasNext()){
            System.out.println("Registering new component");
            BaseComponent component = iterator.next();
            ComponentsRegistryBoard.getInstance().registerComponent(component.getComponentMeta(),component.getClass());
        }
    }
}
