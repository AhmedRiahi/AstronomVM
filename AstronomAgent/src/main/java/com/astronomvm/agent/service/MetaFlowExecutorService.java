package com.astronomvm.agent.service;

import com.astronomvm.core.exception.MetaFlowNotFoundException;
import com.astronomvm.core.model.data.EnvironmentVariables;
import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.kernel.engine.AstronomEngine;
import lombok.Data;

import java.util.List;

@Data
public class MetaFlowExecutorService {

    private List<MetaFlow> metaFlows;

    public void executeMetaFlow(String metaFlowName){
        MetaFlow metaFlow = this.findMetaFlowByName(metaFlowName);
        metaFlow.setEnvironmentVariables(new EnvironmentVariables());
        AstronomEngine.getInstance().executeWorkflow(metaFlow);
    }

    public MetaFlow findMetaFlowByName(String name){
        return this.metaFlows.stream().filter(metaFlow -> metaFlow.getName().equals(name)).findAny().orElseThrow(() -> new MetaFlowNotFoundException(name));
    }
}
