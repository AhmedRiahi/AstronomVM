package com.astronomvm.mapper;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.core.data.output.ResultFlow;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.meta.ComponentMeta;
import com.astronomvm.core.meta.ParameterMeta;
import com.astronomvm.core.meta.functional.FunctionalModelMeta;
import org.json.JSONObject;

import java.util.Map;

public class FunctionalModelMapperComponent extends AstronomBaseComponent {


    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";
    private static final String FUNCTIONAL_MODEL_REPOSITORY_NAME_PARAMETER_NAME = "FUNCTIONAL_MODEL_REPOSITORY_NAME";
    private static final String FUNCTIONAL_MODEL_META_NAME_PARAMETER_NAME = "FUNCTIONAL_MODEL_META_NAME";
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

        ParameterMeta functionalModelRepositoryNameParameterMeta = new ParameterMeta();
        functionalModelRepositoryNameParameterMeta.setName(FUNCTIONAL_MODEL_REPOSITORY_NAME_PARAMETER_NAME);
        functionalModelRepositoryNameParameterMeta.setType(DataType.FUNCTIONAl_MODEL);

        ParameterMeta functionalModelMetaParameterMeta = new ParameterMeta();
        functionalModelMetaParameterMeta.setName(FUNCTIONAL_MODEL_META_NAME_PARAMETER_NAME);
        functionalModelMetaParameterMeta.setType(DataType.FUNCTIONAl_MODEL);

        ParameterMeta mappingMapParameterMeta = new ParameterMeta();
        mappingMapParameterMeta.setName(MAPPING_MAP_PARAMETER_NAME);
        mappingMapParameterMeta.setType(DataType.OBJECT);


        componentMeta.addParameterMeta(inputFlowParameterMeta);
        componentMeta.addParameterMeta(functionalModelRepositoryNameParameterMeta);
        componentMeta.addParameterMeta(functionalModelMetaParameterMeta);
        componentMeta.addParameterMeta(mappingMapParameterMeta);
        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {
        String inputFlowParameterName = parametersValues.get(INPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.inputFlowResultSet =  this.getInputResultFlow().getResultSet(inputFlowParameterName);
        String repositoryName = parametersValues.get(FUNCTIONAL_MODEL_REPOSITORY_NAME_PARAMETER_NAME).getString("value");
        String modelName = parametersValues.get(FUNCTIONAL_MODEL_META_NAME_PARAMETER_NAME).getString("value");
        this.functionalModelMeta = this.getFunctionalModelMetaRepository().findOne(repositoryName,modelName);
        //this.mappingMap = (Map<String, String>) this.inputParameters.getParameterByName(MAPPING_MAP_PARAMETER_NAME).getValue().getUnderlying();
    }

    @Override
    public ResultFlow execute() {
        return null;
    }
}
