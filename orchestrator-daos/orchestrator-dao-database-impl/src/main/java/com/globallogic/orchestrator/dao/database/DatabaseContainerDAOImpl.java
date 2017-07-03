package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ContainerDatabaseConnector;
import com.globallogic.orchestrator.dao.database.mapper.ContainerRowMapper;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class DatabaseContainerDAOImpl implements DatabaseContainerDAO {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseContainerDAOImpl.class);

    @Autowired
    private ContainerDatabaseConnector connector;

    @Autowired
    private ContainerRowMapper mapper;

    @Override
    @Transactional
    public void save(final Set<ContainerDto> containers) {
        LOG.debug("Save containers -> " + containers);
        containers.forEach(el -> connector.insert(el.getId(), el.getStatus(), el.getNodeName(), el.getServiceName()));
    }

    @Override
    public Set<ContainerDto> load() {
        LOG.debug("Load containers");
        return connector.getAll(mapper);
    }

    @Override
    public ContainerDto getById(final String id) {
        LOG.debug("Get a container by id -> " + id);
        return connector.getById(id, mapper);
    }

    @Override
    public void remove(final String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final String id, final String status, final String node, final String server) {
        LOG.debug("Insert a container");
        connector.insert(id, status, node, server);
    }
}
