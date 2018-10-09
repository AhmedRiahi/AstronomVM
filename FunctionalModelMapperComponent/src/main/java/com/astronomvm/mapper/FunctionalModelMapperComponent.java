package com.astronomvm.mapper;

import com.astronomvm.component.AstronomBaseComponent;
import com.astronomvm.core.model.data.output.ResultFlow;
import com.astronomvm.core.model.data.output.ResultSet;
import com.astronomvm.core.model.data.row.RowHeader;
import com.astronomvm.core.model.data.type.DataType;
import com.astronomvm.core.model.meta.ComponentMeta;
import com.astronomvm.core.model.meta.ParameterMeta;
import com.astronomvm.core.model.meta.functional.FunctionalModelMeta;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FunctionalModelMapperComponent extends AstronomBaseComponent {


    private static final String INPUT_FLOW_NAME_PARAMETER_NAME = "INPUT_FLOW_NAME";
    private static final String FUNCTIONAL_MODEL_REPOSITORY_NAME_PARAMETER_NAME = "FUNCTIONAL_MODEL_REPOSITORY_NAME";
    private static final String FUNCTIONAL_MODEL_META_NAME_PARAMETER_NAME = "FUNCTIONAL_MODEL_META_NAME";
    private static final String MAPPING_MAP_PARAMETER_NAME = "MAPPING_MAP";
    private static final String OUTPUT_FLOW_NAME_PARAMETER_NAME = "OUTPUT_FLOW_NAME";


    private ResultSet inputFlowResultSet;
    private FunctionalModelMeta functionalModelMeta;
    private Map<String,String> mappingMap = new HashMap<>();
    private String outputFlowName;

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

        ParameterMeta outputFlowParameterMeta = new ParameterMeta();
        outputFlowParameterMeta.setName(OUTPUT_FLOW_NAME_PARAMETER_NAME);
        outputFlowParameterMeta.setType(DataType.STRING);


        componentMeta.addParameterMeta(inputFlowParameterMeta);
        componentMeta.addParameterMeta(functionalModelRepositoryNameParameterMeta);
        componentMeta.addParameterMeta(functionalModelMetaParameterMeta);
        componentMeta.addParameterMeta(mappingMapParameterMeta);
        componentMeta.addParameterMeta(outputFlowParameterMeta);
        return componentMeta;
    }

    @Override
    public void parseInputParameters(Map<String, JSONObject> parametersValues) {
        String inputFlowParameterName = parametersValues.get(INPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        this.inputFlowResultSet =  this.getInputResultFlow().getResultSet(inputFlowParameterName);
        this.outputFlowName = parametersValues.get(OUTPUT_FLOW_NAME_PARAMETER_NAME).getString("value");
        String repositoryName = parametersValues.get(FUNCTIONAL_MODEL_REPOSITORY_NAME_PARAMETER_NAME).getString("value");
        String modelName = parametersValues.get(FUNCTIONAL_MODEL_META_NAME_PARAMETER_NAME).getString("value");
        this.functionalModelMeta = this.getFunctionalModelMetaRepository().findOne(repositoryName,modelName);
        JSONArray mappingMapJsonArray = parametersValues.get(MAPPING_MAP_PARAMETER_NAME).getJSONArray("value");
        for(int i=0; i< mappingMapJsonArray.length() ; i++){
            String sourceColumnName = mappingMapJsonArray.getJSONObject(i).getString("source");
            String targetColumnName = mappingMapJsonArray.getJSONObject(i).getString("target");
            this.mappingMap.put(sourceColumnName,targetColumnName);
        }
    }

    @Override
    public ResultFlow execute() {
        ResultSet resultSet = new ResultSet();
        RowHeader newRowHeader = new RowHeader();

        this.inputFlowResultSet.getRowHeader().getColumnsNames().stream().forEach(columnName -> {
            String attributeName = this.mappingMap.get(columnName);
            DataType attributeType = this.functionalModelMeta.getAttributeType(attributeName);
            newRowHeader.addColumn(attributeName,attributeType);
        });

        resultSet.setRowHeader(newRowHeader);
        resultSet.setRows(inputFlowResultSet.getRows());

        ResultFlow resultFlow = new ResultFlow();
        resultFlow.addResultSet(this.outputFlowName,resultSet);
        return resultFlow;
    }
}
