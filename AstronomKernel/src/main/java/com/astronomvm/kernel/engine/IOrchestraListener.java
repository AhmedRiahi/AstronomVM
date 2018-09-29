package com.astronomvm.kernel.engine;

public interface IOrchestraListener {

    void onOrchestraStartEvent();
    void onOrchestraFinishEvent();
    void onStepStartEvent();
    void onStepFinishEvent();
}
