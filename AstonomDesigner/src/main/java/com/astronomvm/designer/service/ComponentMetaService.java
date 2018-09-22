package com.astronomvm.designer.service;

import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.kernel.spi.ComponentsLoader;
import com.astronomvm.kernel.spi.ComponentsRegistryBoard;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ComponentMetaService {

    public void reloadComponents(){
        ComponentsLoader.getInstance().loadComponents();
    }

    public Set<ComponentMeta> getComponentsMeta(){
        return ComponentsRegistryBoard.getInstance().getAllRegisteredComponents();
    }
}
