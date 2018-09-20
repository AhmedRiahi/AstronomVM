package com.astronomvm.designer.persistence.entity.process;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TRANSITION")
public class TransitionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.REFRESH)
    private StepEntity fromStep;

    @OneToOne(cascade = CascadeType.REFRESH)
    private StepEntity toStep;

    @Column
    private Boolean isErrorTransition;
}
