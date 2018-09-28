package com.astronomvm.simulator.rest;

import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.simulator.service.AstronomEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/astronomEngine")
public class AstronomEngineRest {

    @Autowired
    private AstronomEngineService astronomEngineService;

    @PostMapping(path = "/execute")
    public void executeWorkflow(@RequestBody MetaFlow astronomMetaFlow){
        this.astronomEngineService.executeWorkflow(astronomMetaFlow);
    }
}
