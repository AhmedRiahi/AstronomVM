package com.astronomvm.kernel.spi;

import com.astronomvm.component.BaseComponent;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.ServiceLoader;

@Slf4j
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
            log.info("Registering new component "+component.getComponentMeta().getName());
            ComponentsRegistryBoard.getInstance().registerComponent(component.getComponentMeta(),component.getClass());
        }
    }
}
