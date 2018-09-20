package com.astronomvm.designer.rest;


import com.astronomvm.designer.persistence.entity.configuration.DatabaseConnectionEntity;
import com.astronomvm.designer.service.DatabaseConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/databaseConnection")
public class DatabaseConnectionRest {

    @Autowired
    private DatabaseConnectionService databaseConnectionService;


    @RequestMapping(path = "/getAll",method = RequestMethod.GET)
    public List<DatabaseConnectionEntity> getAll(){
        return this.databaseConnectionService.getAll();
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public DatabaseConnectionEntity create(@RequestBody DatabaseConnectionEntity databaseConnectionEntity){
        return this.databaseConnectionService.create(databaseConnectionEntity);
    }

}
