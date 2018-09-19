package com.astronomvm.core.data.output;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultFlow {

    private Map<String,ResultSet> resultSetMap = new HashMap<>();

    public void addResultSet(String flowName,ResultSet resultSet){
        this.resultSetMap.put(flowName,resultSet);
    }

}
