package com.astronomvm.functionalRepository.service;


import com.astronomvm.core.meta.functional.FunctionalModelMeta;
import com.astronomvm.functionalRepository.exception.FunctionalModelNotFoundException;
import com.astronomvm.functionalRepository.persistence.entity.FunctionalModelEntity;
import com.astronomvm.functionalRepository.persistence.repository.IFunctionalModelRepository;
import com.astronomvm.functionalRepository.transformer.FunctionalModelTransformer;
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
