package com.astronomvm.designer.rest;

import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.designer.service.AstronomFlowExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/astronomFlowExecutor")
public class AstronomFlowExecutorRest {

    @Autowired
    private AstronomFlowExecutorService astronomFlowExecutorService;

    @PostMapping(path = "execute")
    public void executeFlow(AstronomMetaFlow astronomMetaFlow,String simulatorId){
        this.astronomFlowExecutorService.executeFlow(astronomMetaFlow,simulatorId);
    }

}
