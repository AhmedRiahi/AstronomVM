package com.astronomvm.simulator.monitoring;

import com.astronomvm.kernel.engine.IOrchestraListener;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Data
public class OrchestraEventsPublisher implements IOrchestraListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private String flowToken;

    @Override
    public void onOrchestraStartEvent() {
        simpMessagingTemplate.convertAndSend("orchestra/"+this.flowToken,"Orchestra Started");

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
