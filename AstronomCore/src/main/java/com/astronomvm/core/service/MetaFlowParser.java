package com.astronomvm.core.service;

import com.astronomvm.core.meta.MetaFlow;
import com.astronomvm.core.meta.StepMeta;
import com.astronomvm.core.meta.TransitionMeta;
import org.json.JSONArray;
import org.json.JSONObject;

public class MetaFlowParser {

    private MetaFlowParser(){}

    public static MetaFlow parseMetaFlow(String fileContent){

        MetaFlow metaFlow = new MetaFlow();
        JSONObject jsonObject = new JSONObject(fileContent);
        metaFlow.setName(jsonObject.getString("name"));
        JSONArray stepsArray = jsonObject.getJSONArray("steps");
        for(int i=0; i < stepsArray.length(); i++){
            StepMeta stepMeta = MetaFlowParser.parseStepMeta(stepsArray.getJSONObject(i));
            metaFlow.addStepMeta(stepMeta);
        }

        JSONArray transitionsArray = jsonObject.getJSONArray("steps");
        for(int i=0; i < transitionsArray.length(); i++){
            TransitionMeta transitionMeta = MetaFlowParser.parseTransitionMeta(transitionsArray.getJSONObject(i));
            metaFlow.addTransition(transitionMeta);
        }
        return metaFlow;
    }

    private static StepMeta parseStepMeta(JSONObject jsonObject){
        StepMeta stepMeta = new StepMeta();

        return stepMeta;
    }

    private static TransitionMeta parseTransitionMeta(JSONObject jsonObject){
        TransitionMeta transitionMeta = new TransitionMeta();

        return transitionMeta;
    }
}
