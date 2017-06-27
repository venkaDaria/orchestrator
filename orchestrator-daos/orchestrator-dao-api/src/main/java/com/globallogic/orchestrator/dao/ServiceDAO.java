package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.ServiceDto;

import java.util.Set;

public interface ServiceDAO {

    void save(final Set<ServiceDto> services);

    Set<ServiceDto> load();

    ServiceDto getByName(final String name);
}
