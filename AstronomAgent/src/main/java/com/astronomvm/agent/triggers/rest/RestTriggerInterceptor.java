package com.astronomvm.agent.triggers.rest;


import com.astronomvm.agent.triggers.TriggerInterceptor;
import com.astronomvm.agent.triggers.TriggersManager;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTrigger;
import com.sun.net.httpserver.HttpExchange;


public class RestTriggerInterceptor extends TriggerInterceptor {

    private RestMetaFlowTrigger restMetaFlowTrigger;

    public RestTriggerInterceptor(TriggersManager triggersManager, RestMetaFlowTrigger trigger){
        super(triggersManager);
        this.restMetaFlowTrigger = trigger;
    }

    public void handleRequest(HttpExchange exchange) {
        this.triggersManager.triggerMetaFlowExecution(restMetaFlowTrigger.getMetaFlowName());
    }
}
