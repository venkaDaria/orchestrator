package com.globallogic.orchestrator.connector.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ContainerDatabaseConnectorImpl extends DatabaseConnector {
    private static final String INSERT_CONTAINER_QUERY = "INSERT INTO containers VALUES(?,?,?,?)";
    private static final String GET_ALL_CONTAINERS_QUERY = "SELECT * FROM containers";

    @Override
    public void insert(final Connection con, final String... params) {
        validate(4, "container", params);
        insert(con, INSERT_CONTAINER_QUERY, params);
    }

    @Override
    public Set<String[]> getAll(final Connection con) throws SQLException {
        return getAll(con, GET_ALL_CONTAINERS_QUERY);
    }

    @Override
    protected String[] extract(final ResultSet rs) throws SQLException {
        return new String[]{
                rs.getString("id"),
                rs.getString("status"),
                rs.getString("node"),
                rs.getString("service")
        };
    }
}
