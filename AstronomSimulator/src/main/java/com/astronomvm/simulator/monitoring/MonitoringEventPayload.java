package com.astronomvm.simulator.monitoring;

import lombok.Data;

@Data
public class MonitoringEventPayload {

    private MonitoringEvent event;
    private Object info;
}
