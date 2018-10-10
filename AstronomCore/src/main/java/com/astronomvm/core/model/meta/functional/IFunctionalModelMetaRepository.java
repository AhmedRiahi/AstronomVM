package com.astronomvm.core.model.meta.functional;

public interface IFunctionalModelMetaRepository {

    FunctionalModelMeta findOne(String repositoryName,String modelName);
}
