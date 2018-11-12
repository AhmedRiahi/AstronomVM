package com.astronomvm.core.model.meta.operation;

import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.core.model.meta.operation.trigger.MetaFlowTriggerMeta;
import lombok.Data;

@Data
public class OperationMeta {

    private MetaFlowTriggerMeta metaFlowTrigger;
    private MetaFlow metaFlow;
}
