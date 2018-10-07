package com.astronomvm.designer.rest;


import com.astronomvm.core.data.EnvironmentVariables;
import com.astronomvm.designer.service.EnvironmentVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/environmentVariables")
public class EnvironmentVariablesRest {

    @Autowired
    private EnvironmentVariableService environmentVariableService;

    @PostMapping("/create")
    public void create(@RequestBody EnvironmentVariables environmentVariables){
        this.environmentVariableService.create(environmentVariables);
    }
}
