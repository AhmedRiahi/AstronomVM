package com.astronomvm.designer.persistence.repository;

import com.astronomvm.designer.persistence.entity.configuration.EnvironmentVariablesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnvironmentVariablesRepository extends JpaRepository<EnvironmentVariablesEntity,Integer> {
}
