package com.astronomvm.simulator.rest;

import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.simulator.service.AstronomEngineService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/astronomEngine")
public class AstronomEngineRest {


    private AstronomEngineService astronomEngineService;

    @PostMapping(path = "/execute")
    public void executeWorkflow(AstronomMetaFlow astronomMetaFlow){
        this.astronomEngineService.executeWorkflow(astronomMetaFlow);
    }
}
