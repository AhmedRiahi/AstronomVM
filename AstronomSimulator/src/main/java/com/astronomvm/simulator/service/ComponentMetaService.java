package com.astronomvm.simulator.service;

import com.astronomvm.core.model.meta.component.ComponentMeta;
import com.astronomvm.kernel.spi.ComponentsLoader;
import com.astronomvm.kernel.spi.ComponentsRegistryBoard;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class ComponentMetaService {

    @PostConstruct
    public void init(){
        this.reloadComponents();
    }

    public void reloadComponents(){
        ComponentsLoader.getInstance().loadComponents();
    }

    public Set<ComponentMeta> getComponentsMeta(){
        return ComponentsRegistryBoard.getInstance().getAllRegisteredComponents();
    }
}
