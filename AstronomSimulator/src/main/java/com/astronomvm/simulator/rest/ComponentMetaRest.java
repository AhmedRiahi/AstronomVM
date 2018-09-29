package com.astronomvm.simulator.rest;

import com.astronomvm.simulator.service.ComponentMetaService;
import com.astronomvm.core.meta.ComponentMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/componentMeta")
public class ComponentMetaRest {

    @Autowired
    private ComponentMetaService componentMetaService;

    @GetMapping(path = "/getAll")
    public Set<ComponentMeta> getAll(){
        return this.componentMetaService.getComponentsMeta();
    }

    @GetMapping(path = "/reloadComponents")
    public void reloadComponents(){
        this.componentMetaService.reloadComponents();
    }
}
