package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Container;

import java.util.Set;

public interface ContainerService {
    void save(Set<Container> containers);

    Set<Container> load();
}
