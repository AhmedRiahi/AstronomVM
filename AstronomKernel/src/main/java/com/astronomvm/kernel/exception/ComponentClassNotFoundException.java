package com.astronomvm.kernel.exception;

public class ComponentClassNotFoundException extends RuntimeException {

    public ComponentClassNotFoundException(String clazz){
        super(clazz);
    }
}
