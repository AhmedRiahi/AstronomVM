package com.astronomvm.designer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class AstronomDesignerApplication {

    public static void main(String[] args){
        SpringApplication.run(AstronomDesignerApplication.class,args);
    }
}
