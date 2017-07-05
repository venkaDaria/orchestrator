package com.globallogic.orchestrator.dao.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "orchestrator.dao.filesystem.enabled", havingValue = "true")
@PropertySource("classpath:filesystem-dao.properties")
@ComponentScan("com.globallogic.orchestrator.dao.filesystem")
public class DaoFilesystemConfiguration {
    // empty
}
