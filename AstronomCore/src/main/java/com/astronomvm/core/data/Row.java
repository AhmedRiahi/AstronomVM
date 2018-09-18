package com.astronomvm.core.data;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private List<Column> columns = new ArrayList<>();

    public void addColumn(Column column){
        columns .add(column);
    }
}
