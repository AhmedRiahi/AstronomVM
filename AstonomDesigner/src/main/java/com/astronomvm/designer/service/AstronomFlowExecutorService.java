package com.astronomvm.designer.service;


import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.designer.monitoring.ClientsRegistrationService;
import com.astronomvm.designer.service.requester.SimulatorRequester;
import de.codecentric.boot.admin.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AstronomFlowExecutorService {

    @Autowired
    private ClientsRegistrationService clientsRegistrationService;
    @Autowired
    private SimulatorRequester simulatorRequester;

    public void executeFlow(AstronomMetaFlow astronomMetaFlow,String simulatorId){
        Application simulatorApplication = this.clientsRegistrationService.getSimulatorClientById(simulatorId);
        this.simulatorRequester.requestExecuteFlow(simulatorApplication.getServiceUrl(),astronomMetaFlow);
    }

}
