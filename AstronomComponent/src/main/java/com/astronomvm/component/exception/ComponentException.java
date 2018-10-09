package com.astronomvm.component.exception;

public class ComponentException extends Exception {

    public ComponentException(String message){
        super(message);
    }

    public ComponentException(Exception e){
        super(e);
    }
}
