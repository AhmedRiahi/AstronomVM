package com.astronomvm.designer.service;



import com.astronomvm.designer.persistence.entity.component.ComponentDefinitionEntity;
import com.astronomvm.designer.persistence.repository.IComponentDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    @Autowired
    private IComponentDefRepository astronomComponentDefRepository;

    public List<ComponentDefinitionEntity> getAll(){
        return this.astronomComponentDefRepository.findAll();
    }


    public ComponentDefinitionEntity create(ComponentDefinitionEntity astronomComponentDefinitionEntity){
        return this.astronomComponentDefRepository.save(astronomComponentDefinitionEntity);
    }
}
