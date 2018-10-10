package com.astronomvm.core.model.data.row;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Row {

    private List<Column> columns = new ArrayList<>();

    public void addColumn(Column column){
        columns .add(column);
    }

    public Column getColumnAt(int index){
        return this.columns.get(index);
    }
}
