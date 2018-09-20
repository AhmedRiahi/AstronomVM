package com.astronomvm.designer.persistence.repository;


import com.astronomvm.designer.persistence.entity.component.ComponentDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComponentDefRepository extends JpaRepository<ComponentDefinitionEntity,Integer> {
}
