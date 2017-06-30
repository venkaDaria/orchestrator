package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "orchestrator.base.enabled", havingValue = "true")
@PropertySource("base.properties")
@ComponentScan("com.globallogic.orchestrator.base")
public class BaseConfiguration {
}
