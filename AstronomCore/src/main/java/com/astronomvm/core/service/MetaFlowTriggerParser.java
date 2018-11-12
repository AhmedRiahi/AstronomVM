package com.astronomvm.core.service;

import com.astronomvm.core.exception.MetaFlowTriggerNotFoundException;
import com.astronomvm.core.model.meta.operation.trigger.MetaFlowTriggerMeta;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTriggerMeta;
import org.json.JSONObject;

public class MetaFlowTriggerParser {

    private MetaFlowTriggerParser(){}

    public static synchronized MetaFlowTriggerMeta parseMetaFlowTrigger(String content){
        JSONObject jsonObject = new JSONObject(content);
        return parseMetaFlowTrigger(jsonObject);
    }

    public static synchronized MetaFlowTriggerMeta parseMetaFlowTrigger(JSONObject jsonObject){
        MetaFlowTriggerMeta metaFlowTrigger;
        String triggerType = jsonObject.getString("type");
        switch (triggerType){
            case "Rest": metaFlowTrigger = new RestMetaFlowTriggerMeta();break;
            default: throw  new MetaFlowTriggerNotFoundException(triggerType);
        }

        return metaFlowTrigger;
    }
}
