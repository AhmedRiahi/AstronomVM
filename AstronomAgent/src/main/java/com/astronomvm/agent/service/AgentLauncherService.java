package com.astronomvm.agent.service;

import com.astronomvm.kernel.spi.ComponentsLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AgentLauncherService {

    @Value("${astronom.agrent.flowFilePath}")
    private String flowFilePath;

    public void bootstrap(){
        log.info("Bootstrapping Astronom Agent");
        ComponentsLoader.getInstance().loadComponents();
    }

}
