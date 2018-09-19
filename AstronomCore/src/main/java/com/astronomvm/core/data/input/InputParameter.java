package com.astronomvm.core.data.input;

import com.astronomvm.core.data.row.AstronomObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputParameter {

    private String name;
    private AstronomObject value;
}
