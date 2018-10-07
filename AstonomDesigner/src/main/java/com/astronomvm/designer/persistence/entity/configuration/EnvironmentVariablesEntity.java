package com.astronomvm.designer.persistence.entity.configuration;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ENVIRONMENT_VARIABLES")
public class EnvironmentVariablesEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "FUNCTIONAL_REPOSITORY_SERVICE_URL")
    private String functionalRepositoryServiceUrl;
}
