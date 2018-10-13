package com.astronomvm.designer.service.requester;

import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.core.model.meta.component.ComponentMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class SimulatorRequester {

    @Autowired
    private RestTemplate restTemplate;


    public void requestExecuteFlow(String simulatorUrl,MetaFlow metaFlow){
        this.restTemplate.postForEntity(simulatorUrl+"/engine/execute",metaFlow,metaFlow.getClass());
    }

    public Set<ComponentMeta> getAllComponents(String simulatorUrl){
        return this.restTemplate.getForEntity(simulatorUrl+"/componentMeta/getAll",Set.class).getBody();
    }
}
