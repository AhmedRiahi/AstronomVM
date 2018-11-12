package com.astronomvm.agent.triggers.rest;


import com.astronomvm.agent.triggers.TriggerInterceptor;
import com.astronomvm.agent.triggers.TriggersManager;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTriggerMeta;
import com.sun.net.httpserver.HttpExchange;


public class RestTriggerInterceptor extends TriggerInterceptor {

    private RestMetaFlowTriggerMeta restMetaFlowTrigger;

    public RestTriggerInterceptor(TriggersManager triggersManager, RestMetaFlowTriggerMeta trigger){
        super(triggersManager);
        this.restMetaFlowTrigger = trigger;
    }

    public void handleRequest(HttpExchange exchange) {
        this.triggersManager.triggerMetaFlowExecution(restMetaFlowTrigger.getMetaFlowName());
    }
}
