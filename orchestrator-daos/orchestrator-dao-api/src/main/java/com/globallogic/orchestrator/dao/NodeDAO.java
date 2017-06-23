package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.NodeDto;

import java.util.Set;

public interface NodeDAO {

    void save(final Set<NodeDto> node);

    Set<NodeDto> load();

    NodeDto getByName(String name);
}
