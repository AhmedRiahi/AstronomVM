package com.astronomvm.designer.service;



import com.astronomvm.designer.persistence.entity.configuration.DatabaseConnectionEntity;
import com.astronomvm.designer.persistence.repository.IDatabaseConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseConnectionService {

    @Autowired
    private IDatabaseConnectionRepository databaseConnectionRepository;

    public List<DatabaseConnectionEntity> getAll(){
        return this.databaseConnectionRepository.findAll();
    }

    public DatabaseConnectionEntity create(DatabaseConnectionEntity databaseConnectionEntity){
        return this.databaseConnectionRepository.save(databaseConnectionEntity);
    }
}
