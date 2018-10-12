package com.astronomvm.kernel.engine.component;

import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.model.data.EnvironmentVariables;
import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.kernel.components.TestComponent;
import com.astronomvm.kernel.exception.InvalidComponentParameterFormat;
import org.json.JSONObject;
import org.junit.Test;

public class ComponentExecutorTest {


    private ComponentExecutor componentExecutor = new ComponentExecutor();


    @Test(expected = InvalidComponentParameterFormat.class)
    public void testExecuteWithInvalidParameter() throws ComponentException {
        TestComponent testComponent = new TestComponent();
        ResultFlow inputFlow = new ResultFlow();
        EnvironmentVariables environmentVariables = new EnvironmentVariables();
        this.componentExecutor.setLogger(new DefaultComponentLogManager());
        this.componentExecutor.execute(testComponent,inputFlow,"",environmentVariables);
    }
}
