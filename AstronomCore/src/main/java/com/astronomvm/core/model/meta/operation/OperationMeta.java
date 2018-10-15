package com.astronomvm.core.model.meta.operation;

import com.astronomvm.core.model.meta.flow.MetaFlow;
import com.astronomvm.core.model.meta.operation.trigger.MetaFlowTrigger;
import lombok.Data;

@Data
public class OperationMeta {

    private MetaFlowTrigger metaFlowTrigger;
    private MetaFlow metaFlow;
}
