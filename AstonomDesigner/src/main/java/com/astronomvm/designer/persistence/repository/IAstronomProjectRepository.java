package com.astronomvm.designer.persistence.repository;


import com.astronomvm.designer.persistence.entity.project.AstronomProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAstronomProjectRepository extends JpaRepository<AstronomProjectEntity,Integer> {
}
