package com.astronomvm.agent.triggers;

import com.astronomvm.agent.service.MetaFlowExecutorService;
import com.astronomvm.agent.triggers.rest.RestTriggerInterceptor;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTriggerMeta;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;

@Slf4j
public class TriggersManager {

    private MetaFlowExecutorService metaFlowExecutorService;

    public TriggersManager(MetaFlowExecutorService metaFlowExecutorService){
        this.metaFlowExecutorService = metaFlowExecutorService;
    }

    public void triggerMetaFlowExecution(String metaFlowName){
        log.info("Launching metaFlow : "+metaFlowName);
        this.metaFlowExecutorService.executeMetaFlow(metaFlowName);
    }

    public void initRestTriggerContext(RestMetaFlowTriggerMeta restMetaFlowTrigger) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(35000),0);
        HttpContext context = httpServer.createContext(restMetaFlowTrigger.getEndpointName());
        RestTriggerInterceptor restTriggerHandler = new RestTriggerInterceptor(this,restMetaFlowTrigger);
        context.setHandler(restTriggerHandler::handleRequest);
        httpServer.start();
    }
}
