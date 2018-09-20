package com.astronomvm.designer.rest;


import com.astronomvm.designer.persistence.entity.component.ComponentDefinitionEntity;
import com.astronomvm.designer.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/componentDefinition")
public class ComponentRest {

    @Autowired
    private ComponentService astronomComponentService;


    @RequestMapping(path = "/getAll",method = RequestMethod.GET)
    public List<ComponentDefinitionEntity> getAvailableComponents(){
        return this.astronomComponentService.getAll();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ComponentDefinitionEntity create(@RequestBody ComponentDefinitionEntity astronomComponentDefinitionEntity){
        return this.astronomComponentService.create(astronomComponentDefinitionEntity);
    }

}
