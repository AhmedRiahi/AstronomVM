package com.astronomvm.designer.persistence.entity.workflow;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "META_FLOW")
public class MetaFlowEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StepMetaEntity> steps;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TransitionMetaEntity> transitions;
}
