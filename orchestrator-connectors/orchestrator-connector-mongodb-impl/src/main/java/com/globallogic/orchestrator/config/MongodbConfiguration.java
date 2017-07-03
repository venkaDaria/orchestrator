package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "orchestrator.connector.mongodb.enabled", havingValue = "true")
@ComponentScan("com.globallogic.orchestrator.connector.mongodb")
public class MongodbConfiguration {
    // empty
}
