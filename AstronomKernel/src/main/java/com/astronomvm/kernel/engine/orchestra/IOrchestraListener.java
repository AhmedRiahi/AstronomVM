package com.astronomvm.kernel.engine.orchestra;

public interface IOrchestraListener {

    void onOrchestraStartEvent();
    void onOrchestraFinishEvent();
    void onStepStartEvent();
    void onStepFinishEvent();
}
