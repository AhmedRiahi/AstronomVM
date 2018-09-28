package com.astronomvm.designer.rest;

import com.astronomvm.designer.monitoring.ClientsRegistrationService;
import de.codecentric.boot.admin.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/simulator")
public class AstronomSimulatorsRest {

    @Autowired
    private ClientsRegistrationService clientsRegistrationService;

    @GetMapping("/getAll")
    public List<Application> getAllSimulators(){
        return clientsRegistrationService.getSimulatorsClients();
    }

}
