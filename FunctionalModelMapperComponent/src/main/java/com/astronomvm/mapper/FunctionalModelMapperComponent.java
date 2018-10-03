package com.astronomvm.mapper;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;
import com.astronomvm.core.meta.functional.FunctionalModelMeta;

import java.util.Map;

public class FunctionalModelMapperComponent extends BaseComponent {


    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";
    private static final String FUNCTIONAL_MODEL_META_PARAMETER_NAME = "FUNCTIONAL_MODEL_META";
    private static final String MAPPING_MAP_PARAMETER_NAME = "MAPPING_MAP";


    private ResultSet inputFlowResultSet;
    private FunctionalModelMeta functionalModelMeta;
    private Map<String,String> mappingMap;

    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();
        componentMeta.setName("FUNCTIONAL_MODEL_MAPPER");

        ParameterMeta inputFlowParameterMeta = new ParameterMeta();
        inputFlowParameterMeta.setName(INPUT_FLOW_NAME_PARAMETER_NAME);
        inputFlowParameterMeta.setType(DataType.INPUT_FLOW_NAME);

        ParameterMeta functionalModelMetaParameterMeta = new ParameterMeta();
        functionalModelMetaParameterMeta.setName(FUNCTIONAL_MODEL_META_PARAMETER_NAME);
        functionalModelMetaParameterMeta.setType(DataType.FUNCTIONAl_MODEL);

        ParameterMeta mappingMapParameterMeta = new ParameterMeta();
        mappingMapParameterMeta.setName(MAPPING_MAP_PARAMETER_NAME);
        mappingMapParameterMeta.setType(DataType.OBJECT);


        componentMeta.addParameterMeta(inputFlowParameterMeta);
        componentMeta.addParameterMeta(functionalModelMetaParameterMeta);
        componentMeta.addParameterMeta(mappingMapParameterMeta);
        return componentMeta;
    }

    @Override
    public void readInputs() {
        String inputFlowParameterName = this.inputParameters.getParameterByName(INPUT_FLOW_NAME_PARAMETER_NAME).getValue().toString();
        this.inputFlowResultSet = (ResultSet) this.inputParameters.getParameterByName(inputFlowParameterName).getValue().getUnderlying();
        this.functionalModelMeta = (FunctionalModelMeta) this.inputParameters.getParameterByName(FUNCTIONAL_MODEL_META_PARAMETER_NAME).getValue().getUnderlying();
    }

    @Override
    public ResultFlow execute() {
        return null;
    }
}
