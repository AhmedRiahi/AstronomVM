package com.astronomvm.core.data.input;

import com.astronomvm.core.data.row.AstronomObject;
import lombok.Data;

@Data
public class InputParameter {

    private String name;
    private AstronomObject value;
}
