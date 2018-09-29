package com.astronomvm.kernel.engine;

import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.kernel.factory.WokflowBuilder;
import com.astronomvm.kernel.workflow.AstronomWorkflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AstronomEngine {


    private static final AstronomEngine instance = new AstronomEngine();
    private List<Orchestrator> orchestrators = new ArrayList<>();

    private AstronomEngine(){}

    public static AstronomEngine getInstance(){
        return AstronomEngine.instance;
    }


    public void executeWorkflow(MetaFlow flow,IOrchestraListener... orchestraListeners){
        Orchestrator orchestrator = new Orchestrator();
        this.orchestrators.add(orchestrator);
        Arrays.stream(orchestraListeners).forEach(listener -> orchestrator.subscribeOrchestraListener(listener));
        AstronomWorkflow astronomWorkflow = WokflowBuilder.getInstance().buildWorkflow(flow);
        orchestrator.play(astronomWorkflow);
    }
}
