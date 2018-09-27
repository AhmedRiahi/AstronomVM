package com.astronomvm.designer.service.requester;

import com.astronomvm.core.meta.AstronomMetaFlow;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimulatorRequester {

    private RestTemplate restTemplate;


    public void requestExecuteFlow(String simulatorUrl,AstronomMetaFlow astronomMetaFlow){
        this.restTemplate.postForEntity(simulatorUrl+"/astronomEngine/execute",astronomMetaFlow,astronomMetaFlow.getClass());
    }
}
