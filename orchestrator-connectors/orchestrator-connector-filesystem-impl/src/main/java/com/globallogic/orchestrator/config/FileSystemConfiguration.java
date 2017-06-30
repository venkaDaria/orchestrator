package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "orchestrator.connector.filesystem.enabled", havingValue = "true")
@PropertySource("connector-filesystem.properties")
@ComponentScan("com.globallogic.orchestrator.connector.filesystem")
public class FileSystemConfiguration {
    // empty
}
