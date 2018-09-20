package com.astronomvm.designer.transformer;


import com.astronomvm.designer.payload.DatabaseConnection;
import com.astronomvm.designer.persistence.entity.configuration.DatabaseConnectionEntity;
import org.springframework.beans.BeanUtils;

public class DatabaseConnectionTransformer {


    public static DatabaseConnection convertEntity(DatabaseConnectionEntity databaseConnectionEntity){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        BeanUtils.copyProperties(databaseConnectionEntity,databaseConnection);
        return databaseConnection;
    }
}
