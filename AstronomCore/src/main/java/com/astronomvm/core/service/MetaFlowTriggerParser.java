package com.astronomvm.core.service;

import com.astronomvm.core.exception.MetaFlowTriggerNotFoundException;
import com.astronomvm.core.model.meta.operation.trigger.MetaFlowTriggerMeta;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTriggerMeta;
import org.json.JSONObject;


public class MetaFlowTriggerParser {


    public synchronized MetaFlowTriggerMeta parseMetaFlowTrigger(JSONObject jsonObject){

        String triggerType = jsonObject.getString("type");
        switch (triggerType){
            case "Rest": return this.parseRestMetaFlowTriggerMeta(jsonObject);
            default: throw new MetaFlowTriggerNotFoundException(triggerType);
        }
    }

    private RestMetaFlowTriggerMeta parseRestMetaFlowTriggerMeta(JSONObject jsonObject){
        RestMetaFlowTriggerMeta restMetaFlowTriggerMeta = new RestMetaFlowTriggerMeta();
        restMetaFlowTriggerMeta.setEndpointName(jsonObject.getJSONObject("endpoint").getString("name"));
        restMetaFlowTriggerMeta.setMetaFlowName(jsonObject.getString("metaFlowName"));
        return restMetaFlowTriggerMeta;
    }
}
