package com.astronomvm.designer.persistence.entity.process;



import com.astronomvm.designer.persistence.entity.component.ComponentDefinitionEntity;
import com.astronomvm.designer.persistence.entity.component.ComponentParameterDefValueEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "STEP")
public class StepEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(cascade = CascadeType.REFRESH)
    private ComponentDefinitionEntity componentDefinition;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ComponentParameterDefValueEntity> parametersValues;

    @OneToOne(cascade = CascadeType.ALL)
    private GraphicsPropertiesEntity graphicsProperties;

}
