package com.astronomvm.kernel.engine.component;

import com.astronomvm.core.meta.functional.FunctionalModelMeta;
import com.astronomvm.core.meta.functional.IFunctionalModelMetaRepository;
import org.springframework.web.client.RestTemplate;

public class FunctionalModelMetaRepository implements IFunctionalModelMetaRepository {

    private RestTemplate restTemplate;
    private String functionalRepositoryServiceUrl;

    public FunctionalModelMetaRepository(String functionalRepositoryServiceUrl){
        this.restTemplate = new RestTemplate();
        this.functionalRepositoryServiceUrl = functionalRepositoryServiceUrl;
    }

    @Override
    public FunctionalModelMeta findOne(String repositoryName, String modelName) {
        return this.restTemplate.getForEntity(this.functionalRepositoryServiceUrl+"/functionalModel/find/"+repositoryName+"/"+modelName,FunctionalModelMeta.class).getBody();
    }
}
