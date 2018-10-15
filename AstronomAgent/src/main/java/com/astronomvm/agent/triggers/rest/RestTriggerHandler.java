package com.astronomvm.agent.triggers.rest;


import com.astronomvm.agent.triggers.TriggerHandler;
import com.astronomvm.agent.triggers.TriggersManager;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTrigger;
import com.sun.net.httpserver.HttpExchange;


public class RestTriggerHandler extends TriggerHandler {

    private RestMetaFlowTrigger restMetaFlowTrigger;

    public RestTriggerHandler(TriggersManager triggersManager, RestMetaFlowTrigger trigger){
        super(triggersManager);
        this.restMetaFlowTrigger = trigger;
    }

    public void handleRequest(HttpExchange exchange) {
        this.triggersManager.triggerMetaFlowExecution(restMetaFlowTrigger.getMetaFlowName());
    }
}
