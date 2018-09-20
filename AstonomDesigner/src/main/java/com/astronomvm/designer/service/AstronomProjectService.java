package com.astronomvm.designer.service;


import com.astronomvm.designer.persistence.entity.project.AstronomProjectEntity;
import com.astronomvm.designer.persistence.repository.IAstronomProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AstronomProjectService {

    @Autowired
    private IAstronomProjectRepository astronomProjectRepository;

    public List<AstronomProjectEntity> getAllProjects(){
        return this.astronomProjectRepository.findAll();
    }


    public AstronomProjectEntity createProject(AstronomProjectEntity astronomProjectEntity){
        return this.astronomProjectRepository.save(astronomProjectEntity);
    }
}
