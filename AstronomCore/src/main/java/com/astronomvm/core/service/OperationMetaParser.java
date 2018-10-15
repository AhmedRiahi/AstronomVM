package com.astronomvm.core.service;

import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.core.model.meta.operation.OperationMeta;
import com.astronomvm.core.model.meta.operation.trigger.MetaFlowTrigger;
import org.json.JSONObject;

public class OperationMetaParser {


    private static final OperationMetaParser instance = new OperationMetaParser();

    private OperationMetaParser(){}

    public synchronized static OperationMeta parseOperationMeta(String content){
        OperationMeta operationMeta = new OperationMeta();
        JSONObject jsonObject = new JSONObject(content);
        JSONObject triggersJson = jsonObject.getJSONObject("triggers");
        MetaFlowTrigger metaFlowTrigger = MetaFlowTriggerParser.parseMetaFlowTrigger(triggersJson);
        JSONObject metaFlowJson = jsonObject.getJSONObject("metaFlows");
        MetaFlow metaFlow = MetaFlowParser.parseMetaFlow(metaFlowJson);

        operationMeta.setMetaFlow(metaFlow);
        operationMeta.setMetaFlowTrigger(metaFlowTrigger);

        return operationMeta;
    }
}
