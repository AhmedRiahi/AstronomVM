package com.astronomvm.designer.rest;

import com.astronomvm.designer.service.MetaFlowExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/astronomFlowExecutor")
public class MetaFlowExecutorRest {

    @Autowired
    private MetaFlowExecutorService metaFlowExecutorService;

    @GetMapping(path = "execute/{simulatorId}/{operationId}")
    public void executeOperation(@PathVariable("operationId") Integer operationId,@PathVariable("simulatorId") String simulatorId){
        this.metaFlowExecutorService.executeFlow(operationId,simulatorId);
    }

}
