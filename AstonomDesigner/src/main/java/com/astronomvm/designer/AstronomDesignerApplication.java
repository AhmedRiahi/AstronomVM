package com.astronomvm.designer;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@ComponentScan
@SpringBootApplication
@EnableSwagger2
@EnableAdminServer
@EnableAutoConfiguration
public class AstronomDesignerApplication {

    public static void main(String[] args){
        SpringApplication.run(AstronomDesignerApplication.class,args);
    }
}