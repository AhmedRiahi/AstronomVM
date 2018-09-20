package com.astronomvm.designer.persistence.entity.process;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "GRAPHICS_PROPERTIES")
public class GraphicsPropertiesEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer x;

    @Column
    private Integer y;
}
