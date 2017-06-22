package com.globallogic.orchestrator.config;

import com.globallogic.orchestrator.service.interfaces.ConfigurationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationConfig {

    @Bean
    public com.globallogic.orchestrator.model.entity.Configuration getConfiguration(ConfigurationService cs) {
        return cs.load();
    }
}
