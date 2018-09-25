package com.astronomvm.simulator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan
@SpringBootApplication
@EnableSwagger2
public class AstronomSimulatorApplication {


    public static void main(String[] args){
        SpringApplication.run(AstronomSimulatorApplication.class,args);
    }
}
