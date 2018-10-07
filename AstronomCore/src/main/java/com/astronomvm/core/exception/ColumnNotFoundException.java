package com.astronomvm.core.exception;

public class ColumnNotFoundException extends CoreException {

    public ColumnNotFoundException(String name){
        super(name);
    }
}
