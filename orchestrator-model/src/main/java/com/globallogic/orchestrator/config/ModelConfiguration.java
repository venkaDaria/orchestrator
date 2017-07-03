package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "orchestrator.model.enabled", havingValue = "true")
@ComponentScan("com.globallogic.orchestrator.model")
public class ModelConfiguration {
}
