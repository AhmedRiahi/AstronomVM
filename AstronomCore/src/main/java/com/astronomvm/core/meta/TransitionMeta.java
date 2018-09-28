package com.astronomvm.core.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransitionMeta {

    private StepMeta source;
    private StepMeta target;
}
