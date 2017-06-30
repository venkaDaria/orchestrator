package com.globallogic.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.apache.log4j.Logger;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan("com.globallogic.orchestrator")
public class Application {

    private static final Logger LOG = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.debug("Start app");
        SpringApplication.run(Application.class, args);
    }
}

//TODO: logging
//TODO: properties