package com.astronomvm.agent.triggers;

public abstract class TriggerInterceptor {

    protected TriggersManager triggersManager;

    public TriggerInterceptor(TriggersManager triggersManager){
        this.triggersManager = triggersManager;
    }

}
