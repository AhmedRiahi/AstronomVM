package com.astronomvm.functional_repository.rest;

import com.astronomvm.core.model.meta.functional.FunctionalModelMeta;
import com.astronomvm.functional_repository.service.FunctionalModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/functionalModel")
@Slf4j
public class FunctionalModelController {

    @Autowired
    private FunctionalModelService functionalModelService;

    @GetMapping("/find/{repositoryName}/{modelName}")
    public FunctionalModelMeta find(@PathVariable String repositoryName,@PathVariable String modelName){
        return this.functionalModelService.find(repositoryName,modelName);
    }

    @PostMapping("/create/{repositoryName}")
    public void create(@PathVariable String repositoryName,@RequestBody FunctionalModelMeta functionalModelMeta){
        log.info("Received model creation query");
        this.functionalModelService.create(repositoryName,functionalModelMeta);
    }
}
