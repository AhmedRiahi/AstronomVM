package com.astronomvm.agent.triggers.rest;


import com.astronomvm.agent.triggers.TriggerInterceptor;
import com.astronomvm.agent.triggers.TriggersManager;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTriggerMeta;
import com.sun.net.httpserver.HttpExchange;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;

@Slf4j
public class RestTriggerInterceptor extends TriggerInterceptor {

    private RestMetaFlowTriggerMeta restMetaFlowTrigger;

    public RestTriggerInterceptor(TriggersManager triggersManager, RestMetaFlowTriggerMeta trigger){
        super(triggersManager);
        this.restMetaFlowTrigger = trigger;
    }

    public void handleRequest(HttpExchange exchange) {
        this.triggersManager.triggerMetaFlowExecution(restMetaFlowTrigger.getMetaFlowName());
        try {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED,2);
            exchange.getResponseBody().write("ok".getBytes());
            exchange.getResponseBody().close();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
}
