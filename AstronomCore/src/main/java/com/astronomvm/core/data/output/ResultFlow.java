package com.astronomvm.core.data.output;

import com.astronomvm.core.exception.ResultSetNotFoundException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultFlow {

    private Map<String,ResultSet> resultSetMap = new HashMap<>();

    public void addResultSet(String resultSetName,ResultSet resultSet){
        this.resultSetMap.put(resultSetName,resultSet);
    }

    public ResultSet getResultSet(String resultSetName){
        if(!this.resultSetMap.containsKey(resultSetName)) throw new ResultSetNotFoundException(resultSetName);
        return this.resultSetMap.get(resultSetName);
    }
}
