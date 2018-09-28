package com.astronomvm.kernel.engine;

import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.kernel.factory.WokflowBuilder;
import com.astronomvm.kernel.workflow.AstronomWorkflow;

public class AstronomEngine {


    private final static AstronomEngine instance = new AstronomEngine();

    private AstronomEngine(){}

    public static AstronomEngine getInstance(){
        return AstronomEngine.instance;
    }


    public void executeWorkflow(MetaFlow flow){
        AstronomOrchestra astronomOrchestra = new AstronomOrchestra();
        AstronomWorkflow astronomWorkflow = WokflowBuilder.getInstance().buildWorkflow(flow);
        astronomOrchestra.play(astronomWorkflow);
    }
}
