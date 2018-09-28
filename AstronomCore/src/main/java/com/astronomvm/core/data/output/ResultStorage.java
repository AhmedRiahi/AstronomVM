package com.astronomvm.core.data.output;

import com.astronomvm.core.meta.StepMeta;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ResultStorage {

    private Map<String,ResultFlow> stepResults = new HashMap<>();

    public void addStepResultFlow(String stepMetaName,ResultFlow resultFlow){
        this.stepResults.put(stepMetaName,resultFlow);
    }

    public ResultFlow getStepMetaResultFlow(String stepMetaName){
        return this.stepResults.get(stepMetaName);
    }

}
