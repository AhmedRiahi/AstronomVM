package com.astronomvm.simulator.service;


import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.kernel.engine.AstronomEngine;
import com.astronomvm.simulator.monitoring.OrchestraEventsPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;



@Service
public class EngineService {

    @Autowired
    private ApplicationContext applicationContext;

    public void executeWorkflow(MetaFlow metaFlow){
        OrchestraEventsPublisher orchestraEventsPublisher = applicationContext.getBean(OrchestraEventsPublisher.class);
        orchestraEventsPublisher.setFlowToken(this.generateWebSocketFlowToken());
        AstronomEngine.getInstance().executeWorkflow(metaFlow,orchestraEventsPublisher);
    }

    private String generateWebSocketFlowToken(){
        return ((System.currentTimeMillis())*(Math.random()+1)+1000)+"";
    }


}
