package com.astronomvm.core.model.data.row;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AstronomObject {

    private Object underlying;

    public AstronomObject(Object underlying){
        this.underlying = underlying;
    }


    public String toString(){
        return this.underlying.toString();
    }

}
