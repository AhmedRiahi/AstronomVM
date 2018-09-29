package com.astronomvm.core.data.row;

import com.astronomvm.core.data.type.DataType;
import com.astronomvm.core.exception.ColumnNotFoundException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
public class RowHeader {

    private List<String> columnsNames = new ArrayList<>();
    private List<DataType> columnsTypes = new ArrayList<>();

    public Integer getColumnNameIndex(String columnName){
        return IntStream.range(0,this.columnsNames.size()).filter( i-> this.columnsNames.get(i).equals(columnName)).mapToObj(Integer::new).findAny().orElseThrow(ColumnNotFoundException::new);
    }

    public void addColumn(String columnName,DataType type){
        this.columnsNames.add(columnName);
        columnsTypes.add(type);
    }
}
