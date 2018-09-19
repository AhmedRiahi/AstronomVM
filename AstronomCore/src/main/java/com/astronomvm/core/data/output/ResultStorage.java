package com.astronomvm.core.data.output;

import com.astronomvm.core.meta.StepMeta;
import lombok.Data;

import java.util.Map;

@Data
public class ResultStorage {

    private Map<StepMeta,ResultFlow> stepResults;

    public void addStepResult(StepMeta stepMeta,ResultFlow resultFlow){
        this.stepResults.put(stepMeta,resultFlow);
    }
}
