package com.astronomvm.designer.transformer;

import com.astronomvm.designer.payload.AstronomOperation;
import com.astronomvm.designer.payload.AstronomTransition;
import com.astronomvm.designer.persistence.entity.process.OperationEntity;
import com.astronomvm.designer.transformer.StepTransformer;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

public class OperationTransformer {


    public static AstronomOperation convertOperationEntity(OperationEntity operationEntity){
        AstronomOperation astronomOperation = new AstronomOperation();
        BeanUtils.copyProperties(operationEntity,astronomOperation);
        astronomOperation.setSteps(new ArrayList<>());
        //Transform steps
        if(operationEntity.getSteps() != null){
            operationEntity.getSteps().stream().forEach(stepEntity ->{
                astronomOperation.getSteps().add(StepTransformer.convertStepEntity(stepEntity));
            });
        }
        //Transform transition
        astronomOperation.setTransitions(new ArrayList<>());
        if(operationEntity.getTransitions() != null){
            operationEntity.getTransitions().stream().forEach(transitionEntity ->{
                AstronomTransition transition = new AstronomTransition();
                BeanUtils.copyProperties(transitionEntity,transition);
                transition.setFromStep(StepTransformer.convertStepEntity(transitionEntity.getFromStep()));
                transition.setToStep(StepTransformer.convertStepEntity(transitionEntity.getToStep()));
                astronomOperation.getTransitions().add(transition);
            });
        }
        return astronomOperation;
    }
}
