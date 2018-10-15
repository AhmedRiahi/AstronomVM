package com.astronomvm.agent.service;

import com.astronomvm.core.exception.MetaFlowNotFoundException;
import com.astronomvm.core.model.meta.flow.MetaFlow;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MetaFlowExecutionServiceTest {

    private MetaFlowExecutorService metaFlowExecutorService = new MetaFlowExecutorService();


    @Test
    public void testFindMetaFlowByName(){
        List<MetaFlow> metaFlowList = new ArrayList<>();
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.setName("Meta1");
        metaFlowList.add(metaFlow);
        metaFlowExecutorService.setMetaFlows(metaFlowList);
        Assert.assertEquals("Meta1",metaFlowExecutorService.findMetaFlowByName("Meta1").getName());
    }

    @Test(expected = MetaFlowNotFoundException.class)
    public void testFindMetaFlowByNameUnknonFlow(){
        List<MetaFlow> metaFlowList = new ArrayList<>();
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.setName("Meta1");
        metaFlowList.add(metaFlow);
        metaFlowExecutorService.setMetaFlows(metaFlowList);
       metaFlowExecutorService.findMetaFlowByName("MetaX");
    }
}
