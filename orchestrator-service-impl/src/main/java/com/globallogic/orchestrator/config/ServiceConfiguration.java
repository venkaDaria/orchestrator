package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "orchestrator.service.enabled", havingValue = "true")
@PropertySource("service.properties")
@ComponentScan("com.globallogic.orchestrator.service")
public class ServiceConfiguration {
}
