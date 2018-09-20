package com.astronomvm.designer.rest;


import com.astronomvm.designer.persistence.entity.project.AstronomProjectEntity;
import com.astronomvm.designer.service.AstronomProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class AstronomProjectRest {

    @Autowired
    private AstronomProjectService astronomProjectService;


    @RequestMapping(path = "/getAll",method = RequestMethod.GET)
    public List<AstronomProjectEntity> getAll(){
        return this.astronomProjectService.getAllProjects();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public AstronomProjectEntity create(@RequestBody AstronomProjectEntity astronomProjectEntity){
        return this.astronomProjectService.createProject(astronomProjectEntity);
    }
}
