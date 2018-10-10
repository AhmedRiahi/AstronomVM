package com.astronomvm.functional_repository.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "FUNCTIONAL_MODEL")
public class FunctionalModelEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "REPOSITORY_NAME")
    private String repositoryName;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FunctionalAttributeEntity> functionalAttributes;
}
