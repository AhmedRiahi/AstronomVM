package com.astronomvm.designer.service;


import com.astronomvm.designer.persistence.entity.functionalEntity.FunctionalEntityDefEntity;
import com.astronomvm.designer.persistence.repository.IFunctionalEntityDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionalEntityDefService {

    @Autowired
    private IFunctionalEntityDefRepository functionalEntityDefRepository;

    public List<FunctionalEntityDefEntity> getAll(){
        return this.functionalEntityDefRepository.findAll();
    }


    public FunctionalEntityDefEntity create(FunctionalEntityDefEntity functionalEntityDefEntity){
        return this.functionalEntityDefRepository.save(functionalEntityDefEntity);
    }

    public void delete(FunctionalEntityDefEntity functionalEntityDefEntity){
        this.functionalEntityDefRepository.delete(functionalEntityDefEntity.getId());
    }

}
