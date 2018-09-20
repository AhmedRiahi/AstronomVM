package com.astronomvm.designer.transformer;


import com.astronomvm.designer.payload.AstronomProject;
import com.astronomvm.designer.persistence.entity.process.OperationEntity;
import com.astronomvm.designer.persistence.entity.process.StepEntity;
import com.astronomvm.designer.persistence.entity.process.TransitionEntity;
import com.astronomvm.designer.persistence.entity.project.AstronomProjectEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;


public class AstronomProjectTransformer {


    public static AstronomProject convertEntity(AstronomProjectEntity astronomProjectEntity){
        AstronomProject astronomProject = new AstronomProject();
        BeanUtils.copyProperties(astronomProjectEntity,astronomProject);
        return astronomProject;
    }

    public static AstronomProjectEntity convertBean(AstronomProject astronomProject){
        AstronomProjectEntity astronomProjectEntity = new AstronomProjectEntity();
        BeanUtils.copyProperties(astronomProject,astronomProjectEntity);
        astronomProjectEntity.setOperations(new ArrayList<>());
        //Transform operations
        if(astronomProject.getOperations() != null){
            astronomProject.getOperations().stream().forEach(operation -> {
                OperationEntity operationEntity = new OperationEntity();
                BeanUtils.copyProperties(operation,operationEntity);
                operationEntity.setSteps(new ArrayList<>());
                //Transform steps
                if(operation.getSteps() != null){
                    operation.getSteps().stream().forEach(step ->{
                        StepEntity stepEntity = new StepEntity();
                        BeanUtils.copyProperties(step,stepEntity);
                        operationEntity.getSteps().add(stepEntity);
                    });
                }
                //Transform transition
                operationEntity.setTransitions(new ArrayList<>());
                if(operation.getTransitions() != null){
                    operation.getTransitions().stream().forEach(transition ->{
                        TransitionEntity transitionEntity = new TransitionEntity();
                        StepEntity fromStepEntity = new StepEntity();
                        BeanUtils.copyProperties(transition.getFromStep(),fromStepEntity);
                        StepEntity toStepEntity = new StepEntity();
                        BeanUtils.copyProperties(transition.getToStep(),toStepEntity);
                        transitionEntity.setFromStep(fromStepEntity);
                        transitionEntity.setToStep(toStepEntity);
                        operationEntity.getTransitions().add(transitionEntity);
                    });
                }
                astronomProjectEntity.getOperations().add(operationEntity);
            });
        }

        return astronomProjectEntity;
    }
}
