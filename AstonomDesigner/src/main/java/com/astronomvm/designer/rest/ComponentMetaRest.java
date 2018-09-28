package com.astronomvm.designer.rest;

import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.designer.service.ComponentMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Set;

@RestController
@RequestMapping("/componentMeta")
public class ComponentMetaRest {

    @Autowired
    private ComponentMetaService componentMetaService;

    @RequestMapping(path = "/{simulatorId}/getAll",method = RequestMethod.GET)
    public Set<ComponentMeta> getAll(@PathVariable("simulatorId") String simulatorId){
        return this.componentMetaService.getComponentsMeta(simulatorId);
    }

    @RequestMapping(path = "/{simulatorId}/reloadComponents",method = RequestMethod.GET)
    public void reloadComponents(@PathVariable("simulatorId") String simulatorId){
        this.componentMetaService.reloadComponents();
    }
}
