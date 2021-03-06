package com.globallogic.orchestrator.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "orchestrator.dao.database.enabled", havingValue = "true")
@PropertySource("classpath:database-dao.properties")
@ComponentScan("com.globallogic.orchestrator.dao.database")
public class DaoDatabaseConfiguration {
    // empty
}
