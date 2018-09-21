package com.astronomvm.designer.payload;


import lombok.Data;

@Data
public class DatabaseConnection {

    private String name;
    private String hostname;
    private String port;
    private String databaseName;
    private String username;
    private String password;
    private String databaseInterface;
}
