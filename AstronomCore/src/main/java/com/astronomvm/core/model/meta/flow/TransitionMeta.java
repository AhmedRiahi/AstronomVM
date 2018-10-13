package com.astronomvm.core.model.meta.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransitionMeta {

    private StepMeta source;
    private StepMeta target;
    private String sourceFlowName;
    private String targetFlowName;
}
