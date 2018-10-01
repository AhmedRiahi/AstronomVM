package com.astronomvm.kernel.engine.orchestra;

public interface IOrchestraListener {

    void onOrchestraStartEvent();
    void onOrchestraFinishEvent();
    void onStepStartEvent(String stepName);
    void onStepFinishEvent(String stepName);
    void publishLog(String message);
}
