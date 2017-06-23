package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class NodeDatabaseConnectorImpl extends AbstractDatabaseConnector implements NodeDatabaseConnector {

    private static final String INSERT_NODE_QUERY = "INSERT INTO nodes VALUES(?,?)";

    private static final String GET_ALL_NODES_QUERY = "SELECT * FROM nodes";

    private static final String GET_NODE_QUERY = "SELECT * FROM nodes WHERE name = ?";

    @Override
    public void insert(final String... params) {
        validate(2, "node", params);
        insert(INSERT_NODE_QUERY, params);
    }

    @Override
    public <T> Set<T> getAll(RowMapper<T> rowMapper) {
        return getAll(GET_ALL_NODES_QUERY, rowMapper);
    }

    @Override
    public <T> T getByName(String name, RowMapper<T> rowMapper) {
        return get(GET_NODE_QUERY, name, rowMapper);
    }
}
