package com.astronomvm.functionalRepository.rest;

import com.astronomvm.core.meta.functional.FunctionalModelMeta;
import com.astronomvm.functionalRepository.service.FunctionalModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/functionalModel")
@Slf4j
public class FunctionalModelController {

    @Autowired
    private FunctionalModelService functionalModelService;

    @GetMapping("/find/{repositoryName}/{modelName}")
    public FunctionalModelMeta find(String repositoryName,String modelName){
        return this.functionalModelService.find(repositoryName,modelName);
    }

    @PostMapping("/create/{repositoryName}")
    public void create(String repositoryName,FunctionalModelMeta functionalModelMeta){
        log.info("Received model creation query");
        this.functionalModelService.create(repositoryName,functionalModelMeta);
    }
}
