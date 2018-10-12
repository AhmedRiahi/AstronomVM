package com.astronomvm.kernel.exception;

import com.astronomvm.core.exception.CoreException;

public class ComponentCreationException extends CoreException {

    public ComponentCreationException(String name) {
        super(name);
    }
}
