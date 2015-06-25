package com.github.vincent_fuchs.spring_projects.customerBatch.targetSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class TargetRESTSystem {
 
    public static void main(String[] args) {
    	
        SpringApplication.run(TargetRESTSystem.class, args);
    }
}