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
            BaseComponent component = iterator.next();
            System.out.println("Registering new component "+component.getComponentMeta().getName());
            ComponentsRegistryBoard.getInstance().registerComponent(component.getComponentMeta(),component.getClass());
        }
    }
}
