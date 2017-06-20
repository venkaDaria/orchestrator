package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ServiceDatabaseConnectorImpl extends DatabaseConnector {
    private static final String INSERT_SERVICE_QUERY = "INSERT INTO services VALUES(?,?,?,?,?)";
    private static final String GET_ALL_SERVICES_QUERY = "SELECT * FROM services";

    @Override
    public void insert(final Connection con, final String... params) {
        if (params.length != 5) {
            throw new DatabaseOperationException("Can't insert container");
        }
        insert(con, INSERT_SERVICE_QUERY, params);
    }

    @Override
    public Set<String[]> getAll(final Connection con) throws SQLException {
        return getAll(con, GET_ALL_SERVICES_QUERY);
    }

    @Override
    protected String[] extract(final ResultSet rs) throws SQLException {
        return new String[] {
            rs.getString("name"),
            rs.getString("image"),
            rs.getString("roles"),
            rs.getString("ports"),
            rs.getString("volumes")
        };
    }
}
