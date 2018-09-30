package com.astronomvm.simulator.monitoring;

import com.astronomvm.core.service.IComponentLogManager;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentLogManager implements IComponentLogManager {

    @Autowired
    private OrchestraEventsPublisher orchestraEventsPublisher;

    @Override
    public void log(String message){
        this.orchestraEventsPublisher.publishLog(message);
    }

}
