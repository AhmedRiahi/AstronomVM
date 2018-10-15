package com.astronomvm.core.service;

import com.astronomvm.core.exception.MetaFlowTriggerNotFoundException;
import com.astronomvm.core.model.meta.operation.trigger.MetaFlowTrigger;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTrigger;
import org.json.JSONObject;

public class MetaFlowTriggerParser {

    private static final MetaFlowTriggerParser instance = new MetaFlowTriggerParser();

    private MetaFlowTriggerParser(){}

    public synchronized static MetaFlowTrigger parseMetaFlowTrigger(String content){
        JSONObject jsonObject = new JSONObject(content);
        return parseMetaFlowTrigger(jsonObject);
    }

    public synchronized static MetaFlowTrigger parseMetaFlowTrigger(JSONObject jsonObject){
        MetaFlowTrigger metaFlowTrigger;
        String triggerType = jsonObject.getString("type");
        switch (triggerType){
            case "Rest": metaFlowTrigger = new RestMetaFlowTrigger();break;
            default: throw  new MetaFlowTriggerNotFoundException(triggerType);
        }

        return metaFlowTrigger;
    }
}
