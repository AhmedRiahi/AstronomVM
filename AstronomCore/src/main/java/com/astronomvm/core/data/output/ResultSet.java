package com.astronomvm.core.data.output;

import com.astronomvm.core.data.Row;

import java.util.ArrayList;
import java.util.List;

public class ResultSet {

    private List<Row> rows = new ArrayList<>();

    public void addRow(Row row){
        this.rows.add(row);
    }
}
