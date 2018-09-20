package com.astronomvm.designer.persistence.entity.component;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "COMPONENT_DEFINITION")
public class ComponentDefinitionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;


    @OneToMany(cascade = CascadeType.ALL)
    private List<ComponentParameterDefEntity> parametersDefs;

}
