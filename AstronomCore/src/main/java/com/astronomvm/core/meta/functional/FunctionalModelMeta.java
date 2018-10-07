package com.astronomvm.core.meta.functional;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
public class FunctionalModelMeta {

    private String name;
    private List<FunctionalAttributeMeta> attributes ;
}
