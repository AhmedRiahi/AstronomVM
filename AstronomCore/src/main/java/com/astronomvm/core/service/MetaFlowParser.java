package com.astronomvm.core.service;

import com.astronomvm.core.exception.StepMetaNotFoundException;
import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.core.model.meta.flow.StepMeta;
import com.astronomvm.core.model.meta.flow.TransitionMeta;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MetaFlowParser {

    private final static MetaFlowParser instance = new MetaFlowParser();

    private MetaFlowParser(){}

    public static MetaFlowParser getInstance(){
        return MetaFlowParser.instance;
    }

    public static MetaFlow parseMetaFlow(String content){
        JSONObject jsonObject = new JSONObject(content);
        return parseMetaFlow(jsonObject);
    }

    public static MetaFlow parseMetaFlow(JSONObject jsonObject){
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.setName(jsonObject.getString("name"));
        JSONArray stepsArray = jsonObject.getJSONArray("steps");
        for(int i=0; i < stepsArray.length(); i++){
            StepMeta stepMeta = instance.parseStepMeta(stepsArray.getJSONObject(i));
            metaFlow.addStepMeta(stepMeta);
        }

        JSONArray transitionsArray = jsonObject.getJSONArray("transitions");
        for(int i=0; i < transitionsArray.length(); i++){
            TransitionMeta transitionMeta = instance.parseTransitionMeta(transitionsArray.getJSONObject(i),metaFlow.getStepMetaList());
            metaFlow.addTransition(transitionMeta);
        }
        return metaFlow;
    }

    private StepMeta parseStepMeta(JSONObject jsonObject){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setComponentName(jsonObject.getString("componentName"));
        stepMeta.setParametersValues(jsonObject.getJSONObject("parameters").toString());
        return stepMeta;
    }

    private TransitionMeta parseTransitionMeta(JSONObject jsonObject,List<StepMeta> steps){
        TransitionMeta transitionMeta = new TransitionMeta();
        String sourceStepName = jsonObject.getString("sourceStepName");
        String targetStepName = jsonObject.getString("targetStepName");
        StepMeta sourceStep = steps.stream().filter(step -> step.getName().equals(sourceStepName)).findAny().orElseThrow(() -> new StepMetaNotFoundException(sourceStepName));
        StepMeta targetStep = steps.stream().filter(step -> step.getName().equals(targetStepName)).findAny().orElseThrow(() -> new StepMetaNotFoundException(targetStepName));

        transitionMeta.setSource(sourceStep);
        transitionMeta.setTarget(targetStep);
        transitionMeta.setSourceFlowName(jsonObject.getString("sourceFlowName"));
        transitionMeta.setSourceFlowName(jsonObject.getString("targetFlowName"));
        return transitionMeta;
    }
}
