package com.globallogic.orchestrator.dao.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "orchestrator.dao.filesystem.enabled", havingValue = "true")
@ComponentScan("com.globallogic.orchestrator.dao.filesystem")
public class DaoFilesystemConfiguration {
}
