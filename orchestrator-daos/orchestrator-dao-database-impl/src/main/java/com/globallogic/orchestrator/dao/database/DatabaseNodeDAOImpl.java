package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.NodeDatabaseConnector;
import com.globallogic.orchestrator.dao.database.mapper.NodeRowMapper;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DatabaseNodeDAOImpl implements DatabaseNodeDAO {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseNodeDAOImpl.class);

    @Autowired
    private NodeDatabaseConnector connector;

    @Autowired
    private NodeRowMapper mapper;

    @Override
    @Transactional
    public void save(final Set<NodeDto> nodes) {
        LOG.debug("Save nodes -> " + nodes);
        nodes.forEach(el -> connector.insert(el.getName(), el.getRoles()));
    }

    @Override
    public Set<NodeDto> load() {
        LOG.debug("Load nodes");
        return connector.getAll(mapper);
    }

    @Override
    public NodeDto getByName(final String name) {
        LOG.debug("Get a node by name -> " + name);
        return connector.getByName(name, mapper);
    }

    @Override
    public void remove(final String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final String name, final List<String> roles) {
        LOG.debug("Insert a node");
        connector.insert(name, new HashSet<>(roles));
    }
}
