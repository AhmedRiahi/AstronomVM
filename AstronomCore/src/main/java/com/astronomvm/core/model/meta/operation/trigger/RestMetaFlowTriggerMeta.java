package com.astronomvm.core.model.meta.operation.trigger;

import lombok.Data;

@Data
public class RestMetaFlowTriggerMeta extends MetaFlowTriggerMeta {

    private String endpointName;
    private String httpMethodName;
}
