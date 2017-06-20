package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.ContainerDTO;

import java.util.Set;

public interface ContainerDAO {
    void save(final Set<ContainerDTO> containers);

    Set<ContainerDTO> load();
}
