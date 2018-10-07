package com.astronomvm.designer.persistence.repository;


import com.astronomvm.designer.persistence.entity.workflow.MetaFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMetaFlowRepository extends JpaRepository<MetaFlowEntity,Integer> {
}
