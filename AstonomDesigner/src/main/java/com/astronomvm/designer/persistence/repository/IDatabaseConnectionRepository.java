package com.astronomvm.designer.persistence.repository;


import com.astronomvm.designer.persistence.entity.configuration.DatabaseConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDatabaseConnectionRepository extends JpaRepository<DatabaseConnectionEntity,Integer> {
}
