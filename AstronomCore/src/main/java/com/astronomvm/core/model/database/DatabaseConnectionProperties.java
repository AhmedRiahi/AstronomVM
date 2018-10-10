package com.astronomvm.core.model.database;

import lombok.Data;

@Data
public class DatabaseConnectionProperties {

    private String databaseUrl;
    private JDBCDriver jdbcDriver;
    private String schemaName;
    private String username;
    private String password;
}
