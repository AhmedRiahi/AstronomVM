package com.astronomvm.agent.triggers;

public abstract class TriggerHandler {

    protected TriggersManager triggersManager;

    public TriggerHandler(TriggersManager triggersManager){
        this.triggersManager = triggersManager;
    }

}
