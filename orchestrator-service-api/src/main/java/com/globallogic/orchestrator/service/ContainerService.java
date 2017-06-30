package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Container;

import java.util.Set;

public interface ContainerService {

    void save(final Set<Container> containers);

    Set<Container> load();

    Container getById(final String id);

    void remove(String id);

    void add(String id, String status, String node, String server);
}
