package com.astronomvm.designer.persistence.entity.component;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "INPUT_PARAMETER")
public class InputParameterEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private String value;
}
