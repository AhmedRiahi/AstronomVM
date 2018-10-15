package com.astronomvm.core.exception;

public class CoreException extends RuntimeException {

    public CoreException(String msg){
        super(msg);
    }
    public CoreException(Exception e){
        super(e);
    }
}
