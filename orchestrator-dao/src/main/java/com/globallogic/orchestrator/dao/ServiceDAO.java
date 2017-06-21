package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.ServiceDTO;

import java.util.Set;

public interface ServiceDAO {
    void save(final Set<ServiceDTO> services);

    Set<ServiceDTO> load();
}
