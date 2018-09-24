package com.astronomvm.designer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ComponentScan
@SpringBootApplication
@EnableSwagger2
public class AstronomDesignerApplication {

    public static void main(String[] args){
        SpringApplication.run(AstronomDesignerApplication.class,args);
    }
}