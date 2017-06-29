package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.NodeDatabaseConnector;
import com.globallogic.orchestrator.dao.database.mapper.NodeRowMapper;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class DatabaseNodeDAOImpl implements DatabaseNodeDAO {

    @Autowired
    private NodeDatabaseConnector connector;

    @Autowired
    private NodeRowMapper mapper;

    @Override
    @Transactional
    public void save(final Set<NodeDto> nodes) {
        nodes.forEach(el -> connector.insert(el.getName(), el.getRoles()));
    }

    @Override
    public Set<NodeDto> load() {
        return connector.getAll(mapper);
    }

    @Override
    public NodeDto getByName(final String name) {
        return connector.getByName(name, mapper);
    }

    @Override
    public void remove(final String name) {
        throw new UnsupportedOperationException();
    }
}
