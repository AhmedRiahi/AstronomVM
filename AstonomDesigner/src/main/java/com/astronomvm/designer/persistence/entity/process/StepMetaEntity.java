package com.astronomvm.designer.persistence.entity.process;



import com.astronomvm.designer.persistence.entity.component.InputParameter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    private List<InputParameter> inputParameters;

    @OneToOne(cascade = CascadeType.ALL)
    private GraphicsPropertiesEntity graphicsProperties;

}
