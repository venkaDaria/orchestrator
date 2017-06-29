package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Node;

import java.util.Set;

public interface NodeService {

    void save(final Set<Node> node);

    Set<Node> load();

    Node getByName(final String name);

    void remove(String name);
}
