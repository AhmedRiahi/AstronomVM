package com.astronomvm.kernel.engine;

import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.core.interfaces.IComponentLogManager;
import com.astronomvm.kernel.engine.component.DefaultComponentLogManager;
import com.astronomvm.kernel.engine.orchestra.IOrchestraListener;
import com.astronomvm.kernel.engine.orchestra.Orchestrator;
import com.astronomvm.kernel.factory.WorkflowBuilder;
import com.astronomvm.kernel.model.workflow.AstronomWorkflow;

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


    public synchronized void executeWorkflow(MetaFlow flow,IOrchestraListener... orchestraListeners){
        this.executeWorkflow(flow,new DefaultComponentLogManager(),orchestraListeners);
    }

    public synchronized void executeWorkflow(MetaFlow flow,IComponentLogManager componentLogManager,IOrchestraListener... orchestraListeners){
        AstronomWorkflow astronomWorkflow = WorkflowBuilder.getInstance().buildWorkflow(flow);
        Orchestrator orchestrator = new Orchestrator(astronomWorkflow);
        Arrays.stream(orchestraListeners).forEach(orchestrator::subscribeOrchestraListener);
        orchestrator.setComponentLogManager(componentLogManager);
        this.orchestrators.add(orchestrator);
        orchestrator.play();
    }
}
