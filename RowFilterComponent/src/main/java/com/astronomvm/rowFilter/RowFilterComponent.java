package com.astronomvm.rowFilter;

import com.astronomvm.component.BaseComponent;
import com.astronomvm.component.exception.ComponentException;
import com.astronomvm.core.data.output.ResultSet;
import com.astronomvm.core.meta.ComponentMeta;

import java.util.Optional;

public class RowFilterComponent extends BaseComponent {



    @Override
    public ComponentMeta getComponentMeta() {
        ComponentMeta componentMeta = new ComponentMeta();

        

        return componentMeta;
    }

    @Override
    public Optional<ResultSet> execute() throws ComponentException {
        return null;
    }
}
