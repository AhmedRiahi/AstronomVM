package com.astronomvm.designer.persistence.entity.workflow;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TRANSITION_META")
public class TransitionMetaEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.REFRESH)
    private StepMetaEntity fromStep;

    @OneToOne(cascade = CascadeType.REFRESH)
    private StepMetaEntity toStep;

    @Column
    private Boolean isErrorTransition;

    @Column
    private String sourceFlowName;

    @Column
    private String targetFlowName;
}
