package com.astronomvm.simulator.monitoring;

import com.astronomvm.kernel.engine.IOrchestraListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class OrchestraEventsPublisher implements IOrchestraListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private String flowToken;

    @Override
    public void onOrchestraStartEvent() {
        log.info("Sending message to clients using websockets, by chanel /topic/orchestra/"+this.flowToken);
        simpMessagingTemplate.convertAndSend("/topic/orchestra/"+this.flowToken,"Orchestra Started");

    }

    @Override
    public void onOrchestraFinishEvent() {

    }

    @Override
    public void onStepStartEvent() {

    }

    @Override
    public void onStepFinishEvent() {

    }
}
