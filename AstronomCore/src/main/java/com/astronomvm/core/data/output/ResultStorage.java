package com.astronomvm.core.data.output;

import lombok.Data;

import java.util.HashMap;
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

    public ResultSet getStepMetaResultSet(String stepMetaName,String resultSetName){
        return this.getStepMetaResultFlow(stepMetaName).getResultSet(resultSetName);
    }

}
