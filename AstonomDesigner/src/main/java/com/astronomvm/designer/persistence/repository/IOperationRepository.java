package com.astronomvm.designer.persistence.repository;


import com.astronomvm.designer.persistence.entity.process.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationRepository extends JpaRepository<OperationEntity,Integer> {
}
