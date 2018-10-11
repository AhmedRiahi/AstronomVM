package com.astronomvm.kernel.factory;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.meta.ComponentMeta;
import com.astronomvm.kernel.spi.ComponentsRegistryBoard;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.util.Map;



@RunWith(PowerMockRunner.class)
@PrepareForTest(ComponentsRegistryBoard.class)
public class ComponentFactoryTest {


    @Mock
    private ComponentsRegistryBoard componentsRegistryBoard;

    @Before
    public void setup(){
        PowerMockito.mockStatic(ComponentsRegistryBoard.class);
        PowerMockito.when(ComponentsRegistryBoard.getInstance()).thenReturn(this.componentsRegistryBoard);
        PowerMockito.when(this.componentsRegistryBoard.getComponentClass("TestComponent")).thenReturn(TestComponent.class);
        this.componentsRegistryBoard = PowerMockito.mock(ComponentsRegistryBoard.class);
    }


    @Test
    public void testBuildComponent() throws InstantiationException, IllegalAccessException {
        AstronomBaseComponent testComponent = ComponentFactory.getInstance().buildComponent("TestComponent");
        Assert.assertTrue(testComponent.getComponentMeta().getName().equals("TestComponent"));
    }

}
