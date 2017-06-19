package com.globallogic.orchestrator.service.interfaces;

import com.globallogic.orchestrator.model.entity.Node;

import java.util.Set;

public interface NodeService {
    void save(final Set<Node> node);

    Set<Node> load();
}