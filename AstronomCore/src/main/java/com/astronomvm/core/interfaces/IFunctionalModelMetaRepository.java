package com.astronomvm.core.interfaces;

import com.astronomvm.core.model.meta.functional.FunctionalModelMeta;

public interface IFunctionalModelMetaRepository {

    FunctionalModelMeta findOne(String repositoryName, String modelName);
}
