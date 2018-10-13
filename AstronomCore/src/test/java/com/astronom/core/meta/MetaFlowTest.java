package com.astronom.core.meta;

import com.astronomvm.core.exception.StepMetaNotFoundException;
import com.astronomvm.core.model.meta.MetaFlow;
import com.astronomvm.core.model.meta.StepMeta;
import org.junit.Assert;
import org.junit.Test;

public class MetaFlowTest {

    private MetaFlow metaFlow = new MetaFlow();

    @Test
    public void testAddStepMeta(){
        StepMeta stepMeta = new StepMeta();
        metaFlow.addStepMeta(stepMeta);
        Assert.assertEquals(1,metaFlow.getStepMetaList().size());
    }

    @Test
    public void testGetStepMetaByName(){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName("StepName");
        metaFlow.addStepMeta(stepMeta);
        StepMeta resultStepMeta = metaFlow.getStepMetaByName("StepName");
        Assert.assertTrue(resultStepMeta != null);
        Assert.assertTrue(stepMeta.getName().equals(stepMeta.getName()));
    }

    @Test(expected = StepMetaNotFoundException.class)
    public void testGetStepMetaByNameException(){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName("StepName");
        metaFlow.addStepMeta(stepMeta);
        StepMeta resultStepMeta = metaFlow.getStepMetaByName("StepNameX");
    }
}
