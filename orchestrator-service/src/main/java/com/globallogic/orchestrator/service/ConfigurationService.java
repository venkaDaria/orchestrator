package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Configuration;

public interface ConfigurationService {
    void save(Configuration config);

    Configuration load();
}
