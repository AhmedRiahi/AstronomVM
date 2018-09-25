package com.astronomvm.designer.monitoring;

import de.codecentric.boot.admin.event.ClientApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AstronomClientsListener {

    @EventListener
    public void onClientApplicationRegistered(final ClientApplicationEvent event) {
        System.out.println("Registration ----------- "+event.getApplication()+" "+event.getType());
    }
}
