package com.astronomvm.core.data.row;

import com.astronomvm.core.exception.ColumnNotFoundException;
import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class RowHeader {

    private List<String> columnsNames;
    private List<DataType> columnsTypes;

    public Integer getColumnNameIndex(String columnName){
        return IntStream.range(0,this.columnsNames.size()).filter( i-> this.columnsNames.get(i).equals(columnName)).mapToObj(Integer::new).findAny().orElseThrow(ColumnNotFoundException::new);
    }
}
