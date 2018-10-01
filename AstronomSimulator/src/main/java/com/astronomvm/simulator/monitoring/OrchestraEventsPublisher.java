package com.astronomvm.simulator.monitoring;

import com.astronomvm.kernel.engine.orchestra.IOrchestraListener;
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

    private static final String TOPIC_PREFIX = "/topic/orchestra/";

    private String flowToken;

    @Override
    public void onOrchestraStartEvent() {
        MonitoringEventPayload monitoringEventPayload = new MonitoringEventPayload();
        monitoringEventPayload.setEvent(MonitoringEvent.ORCHESTRA_STARTED);
        simpMessagingTemplate.convertAndSend(TOPIC_PREFIX+this.flowToken,monitoringEventPayload);
    }

    @Override
    public void onOrchestraFinishEvent() {
        MonitoringEventPayload monitoringEventPayload = new MonitoringEventPayload();
        monitoringEventPayload.setEvent(MonitoringEvent.ORCHESRTA_FINISHED);
        simpMessagingTemplate.convertAndSend(TOPIC_PREFIX+this.flowToken,monitoringEventPayload);
    }

    @Override
    public void onStepStartEvent(String stepName) {
        MonitoringEventPayload monitoringEventPayload = new MonitoringEventPayload();
        monitoringEventPayload.setEvent(MonitoringEvent.STEP_STARTED);
        monitoringEventPayload.setInfo(stepName);
        simpMessagingTemplate.convertAndSend(TOPIC_PREFIX+this.flowToken,monitoringEventPayload);
    }

    @Override
    public void onStepFinishEvent(String stepName) {
        MonitoringEventPayload monitoringEventPayload = new MonitoringEventPayload();
        monitoringEventPayload.setEvent(MonitoringEvent.STEP_FINISHED);
        monitoringEventPayload.setInfo(stepName);
        simpMessagingTemplate.convertAndSend(TOPIC_PREFIX+this.flowToken,monitoringEventPayload);
    }

    @Override
    public void publishLog(String message){
        MonitoringEventPayload monitoringEventPayload = new MonitoringEventPayload();
        monitoringEventPayload.setEvent(MonitoringEvent.COMPOENNT_LOG);
        monitoringEventPayload.setInfo(message);
        simpMessagingTemplate.convertAndSend(TOPIC_PREFIX+this.flowToken,monitoringEventPayload);
    }
}
