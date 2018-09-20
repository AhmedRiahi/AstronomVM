package com.astronomvm.designer.service;



import com.astronomvm.designer.persistence.repository.IDatabaseConnectionRepository;
import com.astronomvm.designer.persistence.repository.IFunctionalEntityDefRepository;
import com.astronomvm.designer.persistence.repository.IOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AstronomEngineService {


    @Autowired
    private IOperationRepository operationRepository;

    @Autowired
    private IFunctionalEntityDefRepository functionalEntityDefRepository;

    @Autowired
    private IDatabaseConnectionRepository databaseConnectionRepository;

}
