package com.globallogic.orchestrator;

import com.globallogic.orchestrator.dao.LocaleSeparator;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SeparatorHolder.setSeparator(LocaleSeparator.COMMA);
        SpringApplication.run(Application.class, args);
    }
}
