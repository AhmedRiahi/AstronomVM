package com.astronomvm.core.data.row;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private RowHeader rowHeader;
    private List<Column> columns = new ArrayList<>();

    public void addColumn(Column column){
        columns .add(column);
    }
}
