package com.astronomvm.designer.persistence.entity.configuration;


import com.astronomvm.core.data.astonomType.DatabaseInterface;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "DATABASE_CONNECTION")
public class DatabaseConnectionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String hostname;

    @Column
    private String port;

    @Column
    private String databaseName;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private DatabaseInterface databaseInterface;
}
