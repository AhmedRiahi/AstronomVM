package com.astronomvm.kernel.engine;

import com.astronomvm.core.meta.AstronomMetaFlow;
import com.astronomvm.kernel.factory.WokflowBuilder;
import com.astronomvm.kernel.workflow.AstronomWorkflow;

public class AstronomEngine {


    private AstronomOrchestra astronomOrchestra;

    public void executeWorkflow(AstronomMetaFlow flow){
        AstronomWorkflow astronomWorkflow = WokflowBuilder.getInstance().buildWorkflow(flow);
        astronomOrchestra.play(astronomWorkflow);
    }
}
