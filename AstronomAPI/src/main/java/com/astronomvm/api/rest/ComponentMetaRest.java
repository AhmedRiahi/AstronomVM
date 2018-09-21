package com.astronomvm.api.rest;

import com.astronomvm.api.service.ComponentMetaService;
import com.astronomvm.core.meta.ComponentMeta;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/componentMeta")
public class ComponentMetaRest {

    private ComponentMetaService componentMetaService;

    @RequestMapping(path = "/getAll")
    public Set<ComponentMeta> getAll(){
        return this.componentMetaService.getComponentsMeta();
    }
}
