package com.astronomvm.functionalRepository.exception;

import com.astronomvm.core.exception.CoreException;

public class FunctionalModelNotFoundException extends CoreException {

    public FunctionalModelNotFoundException(String name){
        super(name);
    }
}
