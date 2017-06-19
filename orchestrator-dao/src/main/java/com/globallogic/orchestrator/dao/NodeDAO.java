package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.model.entity.Node;

import java.util.Set;

public interface NodeDAO {
    void save(final Set<Node> node);

    Set<Node> load();
}
