package com.astronomvm.designer.rest;



import com.astronomvm.designer.persistence.entity.functionalEntity.FunctionalEntityDefEntity;
import com.astronomvm.designer.service.FunctionalEntityDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/functionalEntity")
public class FunctionalEntityDefRest {

    @Autowired
    private FunctionalEntityDefService functionalEntityDefService;

    @RequestMapping(path = "/getAll",method = RequestMethod.GET)
    public List<FunctionalEntityDefEntity> getAll(){
        return this.functionalEntityDefService.getAll();
    }


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public FunctionalEntityDefEntity create(@RequestBody FunctionalEntityDefEntity functionalEntityDefEntity){
        return this.functionalEntityDefService.create(functionalEntityDefEntity);
    }


    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody FunctionalEntityDefEntity functionalEntityDefEntity){
        this.functionalEntityDefService.delete(functionalEntityDefEntity);
    }
}
