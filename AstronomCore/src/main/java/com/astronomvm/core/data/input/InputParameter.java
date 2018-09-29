package com.astronomvm.core.data.input;

import com.astronomvm.core.data.row.AstronomObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputParameter implements Serializable {

    private String name;
    private AstronomObject value;
}
