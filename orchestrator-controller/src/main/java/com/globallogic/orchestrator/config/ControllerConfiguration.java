package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "orchestrator.controller.enabled", havingValue = "true")
@PropertySource("classpath:controller.properties")
@ComponentScan("com.globallogic.orchestrator.controller")
public class ControllerConfiguration {
    // empty
}
