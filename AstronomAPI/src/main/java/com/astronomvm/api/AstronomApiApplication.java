package com.astronomvm.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class AstronomApiApplication {


    public static void main(String[] args){
        SpringApplication.run(AstronomApiApplication.class,args);
    }
}
