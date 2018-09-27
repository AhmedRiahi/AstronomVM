package com.astronomvm.simulator.service;


import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.kernel.engine.AstronomEngine;
import org.springframework.stereotype.Service;


@Service
public class AstronomEngineService {


    public void executeWorkflow(AstronomMetaFlow astronomMetaFlow){
        AstronomEngine.getInstance().executeWorkflow(astronomMetaFlow);
    }


}
