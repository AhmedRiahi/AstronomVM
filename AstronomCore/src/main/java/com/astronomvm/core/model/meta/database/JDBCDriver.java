package com.astronomvm.core.model.meta.database;


public enum JDBCDriver {
    MYSQL("com.mysql.jdbc.Driver"),
    POSTGRES("org.postgresql.Driver");

    private String className;

    JDBCDriver(String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }


}
