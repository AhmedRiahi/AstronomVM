package com.astronomvm.designer.persistence.entity.workflow;



import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "STEP_META")
public class StepMetaEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COMPONENT_NAME")
    private String componentName;

    @Column(name = "PARAMETERS_VALUES")
    private String parametersValues;

    @OneToOne(cascade = CascadeType.ALL)
    private GraphicsPropertiesEntity graphicsProperties;

}
