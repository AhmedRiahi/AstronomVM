package com.astronomvm.designer.persistence.entity.functionalEntity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "FUNCTIONAL_ENTITY_DEF")
public class FunctionalEntityDefEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<FunctionalEntityFieldDefEntity> fields;
}
