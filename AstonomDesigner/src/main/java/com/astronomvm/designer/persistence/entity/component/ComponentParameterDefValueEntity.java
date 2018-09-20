package com.astronomvm.designer.persistence.entity.component;


import com.astronomvm.designer.persistence.entity.component.ComponentParameterDefEntity;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "COMPONENT_PARAMETER_DEF_VALUE")
public class ComponentParameterDefValueEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private ComponentParameterDefEntity componentParameterDef;

    @Column
    private String value;
}
