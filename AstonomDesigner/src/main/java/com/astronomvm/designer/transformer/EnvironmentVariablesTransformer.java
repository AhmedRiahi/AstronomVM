package com.astronomvm.designer.transformer;

import com.astronomvm.core.model.data.EnvironmentVariables;
import com.astronomvm.designer.persistence.entity.configuration.EnvironmentVariablesEntity;

public class EnvironmentVariablesTransformer {

    private EnvironmentVariablesTransformer(){}

    public static synchronized EnvironmentVariables fromEntity(EnvironmentVariablesEntity entity){
        EnvironmentVariables environmentVariables = new EnvironmentVariables();
        environmentVariables.setFunctionalRepositoryServiceUrl(entity.getFunctionalRepositoryServiceUrl());
        return environmentVariables;
    }


    public static synchronized EnvironmentVariablesEntity fromPayload(EnvironmentVariables payload){
        EnvironmentVariablesEntity environmentVariablesEntity = new EnvironmentVariablesEntity();
        environmentVariablesEntity.setFunctionalRepositoryServiceUrl(payload.getFunctionalRepositoryServiceUrl());
        return environmentVariablesEntity;
    }

}
