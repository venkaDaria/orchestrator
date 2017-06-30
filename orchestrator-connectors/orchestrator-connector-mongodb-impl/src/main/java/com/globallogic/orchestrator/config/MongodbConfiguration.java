package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "orchestrator.connector.mongodb.enabled", havingValue = "true")
@PropertySource("connector-mongodb.properties")
@ComponentScan("com.globallogic.orchestrator.connector.mongodb")
public class MongodbConfiguration {
    // empty
}
