package com.astronomvm.kernel.engine.component;

import com.astronomvm.core.service.IComponentLogManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComponentLogManager implements IComponentLogManager {

    @Override
    public void log(String message){
        log.info(message);
    }

}
