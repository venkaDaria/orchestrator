package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:service.properties")
@ComponentScan("com.globallogic.orchestrator.service")
@ConditionalOnProperty(name = "orchestrator.service.enabled", havingValue = "true")
public class ServiceConfiguration {
    // empty
}
