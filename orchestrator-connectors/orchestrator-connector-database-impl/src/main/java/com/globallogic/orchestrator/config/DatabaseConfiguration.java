package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "orchestrator.connector.database.enabled", havingValue = "true")
@PropertySource("connector-database.properties")
@ComponentScan("com.globallogic.orchestrator.connector.database")
public class DatabaseConfiguration {
    // empty
}
