package com.astronomvm.core.model.meta.operation.trigger;

import lombok.Data;

@Data
public class RestMetaFlowTrigger extends MetaFlowTrigger {

    private String endpointName;
    private String httpMethodName;
}
