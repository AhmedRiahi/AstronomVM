package com.astronomvm.designer.rest;

import com.astronomvm.designer.service.AstronomFlowExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/astronomFlowExecutor")
public class AstronomFlowExecutorRest {

    @Autowired
    private AstronomFlowExecutorService astronomFlowExecutorService;

    @GetMapping(path = "execute/{simulatorId}/{operationId}")
    public void executeOperation(@PathVariable("operationId") Integer operationId,@PathVariable("simulatorId") String simulatorId){
        this.astronomFlowExecutorService.executeFlow(operationId,simulatorId);
    }

}
