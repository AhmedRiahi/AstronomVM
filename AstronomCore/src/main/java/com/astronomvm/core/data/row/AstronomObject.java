package com.astronomvm.core.data.row;

import lombok.Data;

@Data
public class AstronomObject {

    private Object underlying;

    public AstronomObject(Object underlying){
        this.underlying = underlying;
    }


    public String toString(){
        return this.underlying.toString();
    }

}
