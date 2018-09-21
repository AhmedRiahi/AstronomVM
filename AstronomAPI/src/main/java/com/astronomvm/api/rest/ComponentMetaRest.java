package com.astronomvm.api.rest;

import com.astronomvm.api.service.ComponentMetaService;
import com.astronomvm.core.meta.ComponentMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/componentMeta")
public class ComponentMetaRest {

    @Autowired
    private ComponentMetaService componentMetaService;

    @RequestMapping(path = "/getAll",method = RequestMethod.GET)
    public Set<ComponentMeta> getAll(){
        return this.componentMetaService.getComponentsMeta();
    }

    @RequestMapping(path = "/reloadComponents",method = RequestMethod.GET)
    public void reloadComponents(){
        this.componentMetaService.reloadComponents();
    }
}
