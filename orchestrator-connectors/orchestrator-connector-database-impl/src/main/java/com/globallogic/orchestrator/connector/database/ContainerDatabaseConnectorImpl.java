package com.globallogic.orchestrator.connector.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;

@Repository
public class ContainerDatabaseConnectorImpl extends AbstractDatabaseConnector implements ContainerDatabaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(ContainerDatabaseConnectorImpl.class);

    private static final String INSERT_CONTAINER_QUERY = "INSERT INTO containers VALUES(?,?,?,?)";

    private static final String GET_ALL_CONTAINERS_QUERY = "SELECT * FROM containers";

    private static final String GET_CONTAINER_QUERY = "SELECT * FROM containers WHERE id = ?";

    @Override
    @Transactional
    public void insert(final String... params) {
        validate(4, "container", params);
        insert(INSERT_CONTAINER_QUERY, params);

        LOG.debug("Insert a container -> " + Arrays.toString(params));
    }

    @Override
    public <T> Set<T> getAll(final RowMapper<T> mapper) {
        LOG.debug("Get all containers");
        return getAll(GET_ALL_CONTAINERS_QUERY, mapper);
    }

    @Override
    public <T> T getById(final String id, final RowMapper<T> mapper) {
        LOG.debug("Get a container by id  -> " + id);
        return get(GET_CONTAINER_QUERY, id, mapper);
    }
}
