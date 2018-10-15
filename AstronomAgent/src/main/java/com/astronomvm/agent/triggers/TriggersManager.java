package com.astronomvm.agent.triggers;

import com.astronomvm.agent.service.MetaFlowExecutorService;
import com.astronomvm.agent.triggers.rest.RestTriggerHandler;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTrigger;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TriggersManager {

    private MetaFlowExecutorService metaFlowExecutorService;

    public TriggersManager(MetaFlowExecutorService metaFlowExecutorService){
        this.metaFlowExecutorService = metaFlowExecutorService;
    }

    public void triggerMetaFlowExecution(String metaFlowName){
        this.metaFlowExecutorService.executeMetaFlow(metaFlowName);
    }

    public void initRestTriggerContext(RestMetaFlowTrigger restMetaFlowTrigger) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(35000),0);
        HttpContext context = httpServer.createContext(restMetaFlowTrigger.getEndpointName());
        RestTriggerHandler restTriggerHandler = new RestTriggerHandler(this,restMetaFlowTrigger);
        context.setHandler(restTriggerHandler::handleRequest);
        httpServer.start();
    }
}
