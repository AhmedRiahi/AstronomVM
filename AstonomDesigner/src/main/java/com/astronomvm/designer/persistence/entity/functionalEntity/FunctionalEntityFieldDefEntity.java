package com.astronomvm.designer.persistence.entity.functionalEntity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "FUNCTIONAL_ENTITY_FIELD_DEF")
public class FunctionalEntityFieldDefEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Boolean isPrimaryKey;
}
