package com.globallogic.orchestrator.service.interfaces;

import com.globallogic.orchestrator.dao.model.entity.Service;

import java.util.Set;

public interface ServiceService {
    void save(final Set<Service> services);

    Set<Service> load();
}
