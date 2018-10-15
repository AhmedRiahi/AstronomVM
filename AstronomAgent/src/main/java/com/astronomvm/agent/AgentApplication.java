package com.astronomvm.agent;

import com.astronomvm.agent.service.AgentLauncherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class AgentApplication {


    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(AgentApplication.class,args);
        context.getBean(AgentLauncherService.class).bootstrap();
    }
}
