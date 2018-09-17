package com.astronomvm.kernel;

import com.astronomvm.kernel.spi.ComponentsLoader;

public class Main {


    public static void main(String[] args){
        ComponentsLoader.getInstance().loadComponents();
    }
}
