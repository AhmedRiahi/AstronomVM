package com.astronomvm.simulator.service;


import com.astronomvm.core.model.meta.MetaFlow;
import com.astronomvm.kernel.engine.AstronomEngine;
import com.astronomvm.simulator.monitoring.ComponentLogManager;
import com.astronomvm.simulator.monitoring.OrchestraEventsPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;



@Service
public class EngineService {

    @Autowired
    private ApplicationContext applicationContext;

    public void executeWorkflow(MetaFlow metaFlow){
        OrchestraEventsPublisher orchestraEventsPublisher = this.applicationContext.getBean(OrchestraEventsPublisher.class);
        orchestraEventsPublisher.setFlowToken(metaFlow.getName());
        ComponentLogManager componentLogManager = this.applicationContext.getBean(ComponentLogManager.class);
        componentLogManager.setOrchestraEventsPublisher(orchestraEventsPublisher);

        AstronomEngine.getInstance().executeWorkflow(metaFlow,componentLogManager,orchestraEventsPublisher);
    }

}
