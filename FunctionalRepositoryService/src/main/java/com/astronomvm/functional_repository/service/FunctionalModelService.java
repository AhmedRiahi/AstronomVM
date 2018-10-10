package com.astronomvm.functional_repository.service;


import com.astronomvm.core.model.meta.functional.FunctionalModelMeta;
import com.astronomvm.functional_repository.exception.FunctionalModelNotFoundException;
import com.astronomvm.functional_repository.persistence.entity.FunctionalModelEntity;
import com.astronomvm.functional_repository.persistence.repository.IFunctionalModelRepository;
import com.astronomvm.functional_repository.transformer.FunctionalModelTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionalModelService {

    @Autowired
    private IFunctionalModelRepository functionalModelRepository;


    public void create(String repositoryName, FunctionalModelMeta functionalModelMeta) {
        FunctionalModelEntity functionalModelEntity = FunctionalModelTransformer.fromPayload(functionalModelMeta);
        functionalModelEntity.setRepositoryName(repositoryName);
        this.functionalModelRepository.save(functionalModelEntity);
    }

    public FunctionalModelMeta find(String repositoryName, String modelName){
        FunctionalModelEntity functionalModelEntity =  this.functionalModelRepository.findByRepositoryNameAndName(repositoryName,modelName).orElseThrow(() -> new FunctionalModelNotFoundException(modelName));
        return FunctionalModelTransformer.fromEntity(functionalModelEntity);
    }
}
