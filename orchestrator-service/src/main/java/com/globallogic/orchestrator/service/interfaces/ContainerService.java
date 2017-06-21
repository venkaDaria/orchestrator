package com.globallogic.orchestrator.service.interfaces;

import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.entity.Container;

import java.util.Set;

public interface ContainerService {
    void save(final Set<Container> containers);

    Set<ContainerDto> load();
}
