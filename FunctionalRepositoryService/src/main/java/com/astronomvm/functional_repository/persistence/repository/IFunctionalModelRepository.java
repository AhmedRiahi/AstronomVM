package com.astronomvm.functional_repository.persistence.repository;

import com.astronomvm.functional_repository.persistence.entity.FunctionalModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IFunctionalModelRepository extends JpaRepository<FunctionalModelEntity,Integer> {

    Optional<FunctionalModelEntity> findByRepositoryNameAndName(String repositoryName, String modelName);
}
