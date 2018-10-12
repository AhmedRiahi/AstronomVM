package com.astronomvm.kernel.factory;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.kernel.components.TestComponent;
import com.astronomvm.kernel.spi.ComponentsRegistryBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;




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
