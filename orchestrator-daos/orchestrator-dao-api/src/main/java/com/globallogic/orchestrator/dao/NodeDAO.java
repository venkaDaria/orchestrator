package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.dto.NodeDto;

import java.util.List;
import java.util.Set;

public interface NodeDAO {

    void save(final Set<NodeDto> node);

    Set<NodeDto> load();

    NodeDto getByName(final String name);

    void remove(String name);

    void add(String name, List<String> roles);
}
