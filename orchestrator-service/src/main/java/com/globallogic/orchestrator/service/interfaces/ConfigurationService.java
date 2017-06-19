package com.globallogic.orchestrator.service.interfaces;

import com.globallogic.orchestrator.dao.model.entity.Configuration;

public interface ConfigurationService {
    void save(Configuration config);

    Configuration load();
}
