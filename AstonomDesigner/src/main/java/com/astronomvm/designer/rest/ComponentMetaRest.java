package com.astronomvm.designer.rest;

import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.designer.service.ComponentMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/componentMeta")
public class ComponentMetaRest {

    @Autowired
    private ComponentMetaService componentMetaService;

    @GetMapping(path = "/{simulatorId}/getAll")
    public Set<ComponentMeta> getAll(@PathVariable("simulatorId") String simulatorId){
        return this.componentMetaService.getComponentsMeta(simulatorId);
    }

    @GetMapping(path = "/{simulatorId}/reloadComponents")
    public void reloadComponents(@PathVariable("simulatorId") String simulatorId){
        this.componentMetaService.reloadComponents();
    }
}
