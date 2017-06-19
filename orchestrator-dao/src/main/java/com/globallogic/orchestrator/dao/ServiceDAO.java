package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.model.entity.Service;

import java.util.Set;

public interface ServiceDAO {
    void save(final Set<Service> services);

    Set<Service> load();
}
