package com.astronomvm.core.data.output;

import com.astronomvm.core.data.row.Row;
import com.astronomvm.core.data.row.RowHeader;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultSet {

    private RowHeader rowHeader;
    private List<Row> rows = new ArrayList<>();

    public void addRow(Row row){
        this.rows.add(row);
    }
}
