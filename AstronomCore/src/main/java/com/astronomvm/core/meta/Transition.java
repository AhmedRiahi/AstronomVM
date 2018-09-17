package com.astronomvm.core.meta;

import lombok.Data;

@Data
public class Transition {

    private StepMeta source;
    private StepMeta target;
}
