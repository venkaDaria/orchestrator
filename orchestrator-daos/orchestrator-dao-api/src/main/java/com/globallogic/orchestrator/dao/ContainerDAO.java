package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.ContainerDto;

import java.util.Set;

public interface ContainerDAO {

    void save(final Set<ContainerDto> containers);

    Set<ContainerDto> load();

    ContainerDto getById(final String id);

    void remove(String id);

    void add(String id, String status, String node, String server);
}
