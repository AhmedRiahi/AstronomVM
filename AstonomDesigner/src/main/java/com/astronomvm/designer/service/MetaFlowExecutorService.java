package com.astronomvm.designer.service;


import com.astronomvm.core.data.EnvironmentVariables;
import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.designer.monitoring.ClientsRegistrationService;
import com.astronomvm.designer.persistence.entity.configuration.EnvironmentVariablesEntity;
import com.astronomvm.designer.persistence.entity.workflow.MetaFlowEntity;
import com.astronomvm.designer.persistence.repository.IMetaFlowRepository;
import com.astronomvm.designer.service.requester.SimulatorRequester;
import com.astronomvm.designer.transformer.EnvironmentVariablesTransformer;
import com.astronomvm.designer.transformer.MetaFlowTransformer;
import de.codecentric.boot.admin.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MetaFlowExecutorService {

    @Autowired
    private ClientsRegistrationService clientsRegistrationService;
    @Autowired
    private SimulatorRequester simulatorRequester;
    @Autowired
    private IMetaFlowRepository operationRepository;

    @Autowired
    private EnvironmentVariableService environmentVariableService;

    public void executeFlow(Integer operationId,String simulatorId){
        MetaFlowEntity metaFlowEntity = this.operationRepository.findOne(operationId);
        MetaFlow metaFlow = MetaFlowTransformer.fromEntity(metaFlowEntity);
        //TODO Environment variable should be provided by UI
        EnvironmentVariablesEntity environmentVariablesEntity = this.environmentVariableService.findAll().get(0);
        EnvironmentVariables environmentVariables = EnvironmentVariablesTransformer.fromEntity(environmentVariablesEntity);
        metaFlow.setEnvironmentVariables(environmentVariables);
        Application simulatorApplication = this.clientsRegistrationService.getSimulatorClientById(simulatorId);
        this.simulatorRequester.requestExecuteFlow(simulatorApplication.getServiceUrl(),metaFlow);
    }

}
