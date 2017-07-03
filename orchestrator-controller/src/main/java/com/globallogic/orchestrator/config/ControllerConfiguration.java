package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "orchestrator.controller.enabled", havingValue = "true")
@ComponentScan("com.globallogic.orchestrator.controller")
public class ControllerConfiguration {
}
