package com.astronomvm.designer.monitoring;

import de.codecentric.boot.admin.event.ClientApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AstronomClientsListener {

    @Autowired
    private ClientsRegistrationService clientsRegistrationService;

    @EventListener
    public void onClientApplicationRegistered(final ClientApplicationEvent event) {
        if(event.getType().toUpperCase().equals("STATUS_CHANGE")){
            switch(event.getApplication().getStatusInfo().getStatus()){
                case "UP": clientsRegistrationService.registerClient(event.getApplication());break;
                case "OFFLINE": clientsRegistrationService.unregisterClient(event.getApplication());break;
            }
        }
    }
}
