package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.dao.model.entity.Container;

import java.util.Set;

public interface ContainerDAO {
    void save(final Set<Container> containers);

    Set<ContainerDTO> load();
}
