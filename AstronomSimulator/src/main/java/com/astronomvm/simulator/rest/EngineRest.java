package com.astronomvm.simulator.rest;

import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.simulator.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engine")
public class EngineRest {

    @Autowired
    private EngineService engineService;

    @PostMapping(path = "/execute")
    public void executeWorkflow(@RequestBody MetaFlow metaFlow){
        this.engineService.executeWorkflow(metaFlow);
    }
}
