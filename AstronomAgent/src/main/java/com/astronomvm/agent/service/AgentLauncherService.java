package com.astronomvm.agent.service;

import com.astronomvm.agent.exception.TriggerInitException;
import com.astronomvm.agent.triggers.TriggersManager;
import com.astronomvm.core.model.meta.operation.OperationMeta;
import com.astronomvm.core.model.meta.operation.trigger.MetaFlowTrigger;
import com.astronomvm.core.model.meta.operation.trigger.RestMetaFlowTrigger;
import com.astronomvm.core.service.OperationMetaParser;
import com.astronomvm.kernel.spi.ComponentsLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AgentLauncherService {

    @Value("${astronom.agrent.flowFilePath}")
    private String flowFilePath;

    private TriggersManager triggersManager;
    private MetaFlowExecutorService metaFlowExecutorService = new MetaFlowExecutorService();

    public void bootstrap() {
        try {
            log.info("Bootstrapping Astronom Agent");
            ComponentsLoader.getInstance().loadComponents();
            log.info("Loading operation meta");
            OperationMeta operationMeta = this.loadOperationMeta();
            this.metaFlowExecutorService.setMetaFlows(Arrays.asList(operationMeta.getMetaFlow()));
            log.info("Triggers init..");
            this.triggersManager = new TriggersManager(this.metaFlowExecutorService);
            this.buildOperationTrigger(operationMeta.getMetaFlowTrigger());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }

    }


    private OperationMeta loadOperationMeta() throws IOException {
        Path path = Paths.get(this.flowFilePath);
        String content = Files.lines(path).collect(Collectors.joining("\n"));
        return OperationMetaParser.parseOperationMeta(content);
    }

    private void buildOperationTrigger(MetaFlowTrigger metaFlowTrigger){
        if(metaFlowTrigger instanceof RestMetaFlowTrigger){
            try {
                this.triggersManager.initRestTriggerContext((RestMetaFlowTrigger) metaFlowTrigger);
            } catch (IOException e) {
                throw new TriggerInitException(e);
            }
        }
    }

}
