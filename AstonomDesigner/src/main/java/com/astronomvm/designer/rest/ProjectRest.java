package com.astronomvm.designer.rest;


import com.astronomvm.designer.persistence.entity.project.AstronomProjectEntity;
import com.astronomvm.designer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectRest {

    @Autowired
    private ProjectService projectService;


    @GetMapping(path = "/getAll")
    public List<AstronomProjectEntity> getAll(){
        return this.projectService.getAllProjects();
    }

    @PostMapping(path = "/create")
    public AstronomProjectEntity create(@RequestBody AstronomProjectEntity astronomProjectEntity){
        return this.projectService.createProject(astronomProjectEntity);
    }
}
