package com.astronomvm.designer.service.requester;

import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.core.meta.ComponentMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
public class SimulatorRequester {

    @Autowired
    private RestTemplate restTemplate;


    public void requestExecuteFlow(String simulatorUrl,AstronomMetaFlow astronomMetaFlow){
        this.restTemplate.postForEntity(simulatorUrl+"/astronomEngine/execute",astronomMetaFlow,astronomMetaFlow.getClass());
    }

    public Set<ComponentMeta> getAllComponents(String simulatorUrl){
        return this.restTemplate.getForEntity(simulatorUrl+"/componentMeta/getAll",Set.class).getBody();
    }
}
