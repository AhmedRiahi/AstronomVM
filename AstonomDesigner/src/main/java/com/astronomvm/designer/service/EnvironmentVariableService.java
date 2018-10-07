package com.astronomvm.designer.service;

import com.astronomvm.core.data.EnvironmentVariables;
import com.astronomvm.designer.persistence.entity.configuration.EnvironmentVariablesEntity;
import com.astronomvm.designer.persistence.repository.IEnvironmentVariablesRepository;
import com.astronomvm.designer.transformer.EnvironmentVariablesTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentVariableService {

    @Autowired
    private IEnvironmentVariablesRepository environmentVariablesRepository;

    public void create(EnvironmentVariables environmentVariables){
        EnvironmentVariablesEntity environmentVariablesEntity = EnvironmentVariablesTransformer.fromPayload(environmentVariables);
        this.environmentVariablesRepository.save(environmentVariablesEntity);
    }

    public List<EnvironmentVariablesEntity> findAll(){
        return this.environmentVariablesRepository.findAll();
    }
}
