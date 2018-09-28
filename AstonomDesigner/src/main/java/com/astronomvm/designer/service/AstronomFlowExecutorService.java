package com.astronomvm.designer.service;


import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.designer.monitoring.ClientsRegistrationService;
import com.astronomvm.designer.persistence.entity.process.MetaFlowEntity;
import com.astronomvm.designer.persistence.repository.IMetaFlowRepository;
import com.astronomvm.designer.service.requester.SimulatorRequester;
import com.astronomvm.designer.transformer.MetaFlowTransformer;
import de.codecentric.boot.admin.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AstronomFlowExecutorService {

    @Autowired
    private ClientsRegistrationService clientsRegistrationService;
    @Autowired
    private SimulatorRequester simulatorRequester;
    @Autowired
    private IMetaFlowRepository operationRepository;

    public void executeFlow(Integer operationId,String simulatorId){
        MetaFlowEntity metaFlowEntity = this.operationRepository.findOne(operationId);
        MetaFlow metaFlow = MetaFlowTransformer.fromEntity(metaFlowEntity);
        Application simulatorApplication = this.clientsRegistrationService.getSimulatorClientById(simulatorId);
        this.simulatorRequester.requestExecuteFlow(simulatorApplication.getServiceUrl(),metaFlow);
    }

}
