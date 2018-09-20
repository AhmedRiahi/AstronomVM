package com.astronomvm.designer.persistence.repository;



import com.astronomvm.designer.persistence.entity.functionalEntity.FunctionalEntityDefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFunctionalEntityDefRepository extends JpaRepository<FunctionalEntityDefEntity,Integer> {
}
