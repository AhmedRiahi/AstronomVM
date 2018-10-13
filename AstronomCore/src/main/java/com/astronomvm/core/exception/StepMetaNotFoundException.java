package com.astronomvm.core.exception;

import java.util.function.Supplier;

public class StepMetaNotFoundException extends CoreException {

    public StepMetaNotFoundException(String name){
        super(name);
    }

}
