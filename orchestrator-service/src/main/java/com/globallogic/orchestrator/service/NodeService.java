package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Node;

import java.util.Set;

public interface NodeService {
    void write(final String fileName, final Set<Node> node);

    Set<Node> read(final String fileName);
}
