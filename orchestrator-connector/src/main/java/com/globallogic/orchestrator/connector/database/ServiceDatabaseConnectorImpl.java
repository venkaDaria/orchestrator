package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ServiceDatabaseConnectorImpl extends AbstractDatabaseConnector {
    private static final String INSERT_SERVICE_QUERY = "INSERT INTO services VALUES(?,?,?,?,?)";
    private static final String GET_ALL_SERVICES_QUERY = "SELECT * FROM services";

    @Override
    public void insert(final JdbcTemplate jdbcTemplate, final String... params) {
        validate(5, "service", params);
        insert(jdbcTemplate, INSERT_SERVICE_QUERY, params);
    }

    @Override
    public Set<String[]> getAll(final JdbcTemplate jdbcTemplate)  {
        return getAll(jdbcTemplate, GET_ALL_SERVICES_QUERY);
    }

    @Override
    public String[] extract(final ResultSet rs) throws SQLException {
        return new String[]{
                rs.getString("name"),
                rs.getString("image"),
                rs.getString("roles"),
                rs.getString("ports"),
                rs.getString("volumes")
        };
    }
}
