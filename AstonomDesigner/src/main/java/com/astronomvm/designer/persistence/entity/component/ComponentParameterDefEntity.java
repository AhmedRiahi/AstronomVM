package com.astronomvm.designer.persistence.entity.component;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "COMPONENT_PARAMETER_DEF")
public class ComponentParameterDefEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

}
