package com.astronomvm.agent.exception;

import com.astronomvm.core.exception.CoreException;

public class TriggerInitException extends CoreException {

    public TriggerInitException(String msg) {
        super(msg);
    }

    public TriggerInitException(Exception e) {
        super(e);
    }
}
