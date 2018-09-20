package com.astronomvm.designer.rest;


import com.astronomvm.designer.service.AstronomEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engine")
public class AstronomEngineRest {


    @Autowired
    private AstronomEngineService astronomEngineService;


    @RequestMapping(path = "/executeOperation/{operationId}",method = RequestMethod.GET)
    public void executeOperation(@PathVariable Integer operationId){

    }
}
