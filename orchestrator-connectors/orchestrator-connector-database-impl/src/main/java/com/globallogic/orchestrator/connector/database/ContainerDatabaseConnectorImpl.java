package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class ContainerDatabaseConnectorImpl extends AbstractDatabaseConnector implements ContainerDatabaseConnector {

    private static final String INSERT_CONTAINER_QUERY = "INSERT INTO containers VALUES(?,?,?,?)";

    private static final String GET_ALL_CONTAINERS_QUERY = "SELECT * FROM containers";

    private static final String GET_CONTAINER_QUERY = "SELECT * FROM containers WHERE id = ?";

    @Override
    public void insert(final String... params) {
        validate(4, "container", params);
        insert(INSERT_CONTAINER_QUERY, params);
    }

    @Override
    public <T> Set<T> getAll(final RowMapper<T> mapper) {
        return getAll(GET_ALL_CONTAINERS_QUERY, mapper);
    }

    @Override
    public <T> T getById(final String id, final RowMapper<T> mapper) {
        return get(GET_CONTAINER_QUERY, id, mapper);
    }
}
