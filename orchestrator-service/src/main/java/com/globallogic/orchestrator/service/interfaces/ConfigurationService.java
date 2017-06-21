package com.globallogic.orchestrator.service.interfaces;

import com.globallogic.orchestrator.model.entity.Configuration;

public interface ConfigurationService {
    void save(final Configuration config);

    Configuration load();
}
