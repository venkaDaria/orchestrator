package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class ServiceDatabaseConnectorImpl extends AbstractDatabaseConnector implements ServiceDatabaseConnector {

    private static final String INSERT_SERVICE_QUERY = "INSERT INTO services VALUES(?,?,?,?,?)";

    private static final String GET_ALL_SERVICES_QUERY = "SELECT * FROM services";

    private static final String GET_SERVICE_QUERY = "SELECT * FROM services WHERE name = ?";

    @Override
    public void insert(final String... params) {
        validate(5, "service", params);
        insert(INSERT_SERVICE_QUERY, params);
    }

    @Override
    public <T> Set<T> getAll(RowMapper<T> rowMapper) {
        return getAll(GET_ALL_SERVICES_QUERY, rowMapper);
    }

    @Override
    public <T> T getByName(String name, RowMapper<T> rowMapper) {
        return get(GET_SERVICE_QUERY, name, rowMapper);
    }
}
