package com.astronomvm.agent.service;

import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.kernel.engine.AstronomEngine;
import lombok.Data;

import java.util.List;

@Data
public class MetaFlowExecutorService {

    private List<MetaFlow> metaFlows;

    public void executeMetaFlow(String metaFlowName){
        MetaFlow metaFlow = this.findMetaFlowByName(metaFlowName);
        AstronomEngine.getInstance().executeWorkflow(metaFlow);
    }

    private MetaFlow findMetaFlowByName(String name){
        return null;
    }
}
