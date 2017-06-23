package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.ContainerDto;

import java.util.Set;

public interface ContainerDAO {

    void save(final Set<ContainerDto> containers);

    Set<ContainerDto> load();

    ContainerDto getById(String id);
}
