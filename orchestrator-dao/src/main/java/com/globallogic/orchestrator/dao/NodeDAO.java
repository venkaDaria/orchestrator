package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.NodeDTO;

import java.util.Set;

public interface NodeDAO {
    void save(final Set<NodeDTO> node);

    Set<NodeDTO> load();
}
