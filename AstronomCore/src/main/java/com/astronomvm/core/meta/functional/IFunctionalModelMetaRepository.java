package com.astronomvm.core.meta.functional;

public interface IFunctionalModelMetaRepository {

    FunctionalModelMeta findOne(String repositoryName,String modelName);
}
