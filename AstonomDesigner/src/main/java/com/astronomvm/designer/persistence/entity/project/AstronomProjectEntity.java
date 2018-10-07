package com.astronomvm.designer.persistence.entity.project;


import com.astronomvm.designer.persistence.entity.workflow.MetaFlowEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "ASTRONOM_PROJECT")
public class AstronomProjectEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MetaFlowEntity> metaFlows;
}
