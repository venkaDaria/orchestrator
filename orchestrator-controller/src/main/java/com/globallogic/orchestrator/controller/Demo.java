package com.globallogic.orchestrator.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Demo {

    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);
    }
}
