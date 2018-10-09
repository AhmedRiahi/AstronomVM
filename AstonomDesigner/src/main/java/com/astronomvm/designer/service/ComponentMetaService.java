package com.astronomvm.designer.service;

import com.astronomvm.core.model.meta.ComponentMeta;
import com.astronomvm.designer.monitoring.ClientsRegistrationService;
import com.astronomvm.designer.service.requester.SimulatorRequester;
import de.codecentric.boot.admin.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ComponentMetaService {

    @Autowired
    private ClientsRegistrationService clientsRegistrationService;
    @Autowired
    private SimulatorRequester simulatorRequester;

    public void reloadComponents(){
        //TODO send command to astronom simulator
    }

    public Set<ComponentMeta> getComponentsMeta(String simulatorId){
        Application simulatorApplication = this.clientsRegistrationService.getSimulatorClientById(simulatorId);
        return this.simulatorRequester.getAllComponents(simulatorApplication.getServiceUrl());
    }
}
