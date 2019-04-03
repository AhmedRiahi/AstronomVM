package com.astronomvm.core.service;

import com.astronomvm.core.exception.StepMetaNotFoundException;
import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.core.model.meta.flow.StepMeta;
import com.astronomvm.core.model.meta.flow.TransitionMeta;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MetaFlowParser {


    private MetaFlowParser(){}

    public static synchronized MetaFlow parseMetaFlow(JSONObject jsonObject){
        MetaFlow metaFlow = new MetaFlow();
        metaFlow.setName(jsonObject.getString("name"));
        JSONArray stepsArray = jsonObject.getJSONArray("steps");
        for(int i=0; i < stepsArray.length(); i++){
            StepMeta stepMeta = MetaFlowParser.parseStepMeta(stepsArray.getJSONObject(i));
            metaFlow.addStepMeta(stepMeta);
        }

        JSONArray transitionsArray = jsonObject.getJSONArray("transitions");
        for(int i=0; i < transitionsArray.length(); i++){
            TransitionMeta transitionMeta = MetaFlowParser.parseTransitionMeta(transitionsArray.getJSONObject(i),metaFlow.getStepMetaList());
            metaFlow.addTransition(transitionMeta);
        }
        return metaFlow;
    }

    private static synchronized StepMeta parseStepMeta(JSONObject jsonObject){
        StepMeta stepMeta = new StepMeta();
        stepMeta.setName(jsonObject.getString("name"));
        stepMeta.setComponentName(jsonObject.getString("componentName"));
        stepMeta.setParametersValues(jsonObject.getJSONObject("parametersValues").toString());
        return stepMeta;
    }

    private static synchronized TransitionMeta parseTransitionMeta(JSONObject jsonObject,List<StepMeta> steps){
        TransitionMeta transitionMeta = new TransitionMeta();
        String sourceStepName = jsonObject.getString("sourceStepName");
        String targetStepName = jsonObject.getString("targetStepName");
        StepMeta sourceStep = steps.stream().filter(step -> step.getName().equals(sourceStepName)).findAny().orElseThrow(() -> new StepMetaNotFoundException(sourceStepName));
        StepMeta targetStep = steps.stream().filter(step -> step.getName().equals(targetStepName)).findAny().orElseThrow(() -> new StepMetaNotFoundException(targetStepName));

        transitionMeta.setSource(sourceStep);
        transitionMeta.setTarget(targetStep);
        transitionMeta.setSourceFlowName(jsonObject.getString("sourceFlowName"));
        transitionMeta.setTargetFlowName(jsonObject.getString("targetFlowName"));
        return transitionMeta;
    }
}
