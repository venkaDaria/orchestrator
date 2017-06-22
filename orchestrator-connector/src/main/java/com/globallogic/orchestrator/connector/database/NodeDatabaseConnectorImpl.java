package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class NodeDatabaseConnectorImpl extends AbstractDatabaseConnector {
    private static final String INSERT_NODE_QUERY = "INSERT INTO nodes VALUES(?,?)";
    private static final String GET_ALL_NODES_QUERY = "SELECT * FROM nodes";

    @Override
    public void insert(final JdbcTemplate jdbcTemplate, final String... params) {
        validate(2, "node", params);
        insert(jdbcTemplate, INSERT_NODE_QUERY, params);
    }

    @Override
    public Set<String[]> getAll(final JdbcTemplate jdbcTemplate) {
        return getAll(jdbcTemplate, GET_ALL_NODES_QUERY);
    }

    @Override
    public String[] extract(final ResultSet rs) throws SQLException {
        return new String[]{
                rs.getString("name"),
                rs.getString("roles")
        };
    }
}
