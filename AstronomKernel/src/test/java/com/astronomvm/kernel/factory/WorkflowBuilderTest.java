package com.astronomvm.kernel.factory;

import com.astronomvm.core.model.meta.MetaFlow;
import com.astronomvm.core.model.meta.StepMeta;
import com.astronomvm.kernel.components.TestComponent;
import com.astronomvm.kernel.exception.ComponentCreationException;
import com.astronomvm.kernel.model.workflow.AstronomWorkflow;
import com.astronomvm.kernel.spi.ComponentsRegistryBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WorkflowBuilderTest {


    @Before
    public void setup(){
        TestComponent testComponent = new TestComponent();
        ComponentsRegistryBoard.getInstance().registerComponent(testComponent.getComponentMeta(),TestComponent.class);
    }

    @Test
    public void testBuildWorkflowWithValidComponents(){
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.addStepMeta(new StepMeta("StepName","TestComponent","params"));
        AstronomWorkflow astronomWorkflow = WorkflowBuilder.getInstance().buildWorkflow(metaFlow);
        Assert.assertEquals("TestComponent",astronomWorkflow.getComponentByName("TestComponent").getComponentMeta().getName());
    }

    @Test(expected = ComponentCreationException.class)
    public void testBuildWorkflowWithUnknownComponents(){
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.addStepMeta(new StepMeta("StepName","UnknownTestComponent","params"));
        WorkflowBuilder.getInstance().buildWorkflow(metaFlow);
    }
}
