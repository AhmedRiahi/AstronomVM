package com.astronomvm.designer.monitoring;

import de.codecentric.boot.admin.model.Application;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ClientsRegistrationService {

    private static final Object lock = new Object();
    private List<Application> simulatorsClients = new ArrayList<>();
    private List<Application> agentsClients = new ArrayList<>();


    public void registerClient(Application application){
        synchronized (lock){
            String clientType = application.getInfo().getValues().get("client-type").toString();
            if(clientType.equals("Simulator")){
                this.simulatorsClients.add(application);
            }else{
                if(clientType.equals("Agent")){
                    this.agentsClients.add(application);
                }
            }
        }
    }

    public void unregisterClient(Application application){
        synchronized (lock) {
            String clientType = application.getInfo().getValues().get("client-type").toString();
            if (clientType.equals("Simulator")) {
                this.simulatorsClients.remove(application);
            } else {
                if (clientType.equals("Agent")) {
                    this.agentsClients.remove(application);
                }
            }
        }
    }

    public Application getSimulatorClientById(String id){
        return this.simulatorsClients.stream().filter(simulatorsClient -> simulatorsClient.getId().equals(id)).findAny().get();
    }

}
