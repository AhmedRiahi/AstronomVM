package com.astronomvm.core.data.output;

import com.astronomvm.core.meta.StepMeta;
import lombok.Data;

import java.util.Map;

@Data
public class ResultStorage {

    private Map<StepMeta,ResultSet> stepResults;

    public void addStepResult(StepMeta stepMeta,ResultSet resultSet){
        this.stepResults.put(stepMeta,resultSet);
    }
}
