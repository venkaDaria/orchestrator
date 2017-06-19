package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dto.ContainerDTO;
import com.globallogic.orchestrator.model.entity.Container;

import java.util.Set;

public interface ContainerDAO {
    void save(final Set<Container> containers);

    Set<ContainerDTO> load();
}
