package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class NodeDatabaseConnectorImpl extends DatabaseConnector {
    private static final String INSERT_NODE_QUERY = "INSERT INTO nodes VALUES(?,?)";
    private static final String GET_ALL_NODES_QUERY = "SELECT * FROM nodes";

    @Override
    public void insert(final Connection con, final String... params) {
        if (params.length != 2) {
            throw new DatabaseOperationException("Can't insert node");
        }
        insert(con, INSERT_NODE_QUERY, params);
    }

    @Override
    public Set<String[]> getAll(final Connection con) throws SQLException {
        return getAll(con, GET_ALL_NODES_QUERY);
    }

    @Override
    protected String[] extract(final ResultSet rs) throws SQLException {
        return new String[]{
            rs.getString("name"),
            rs.getString("roles")
        };
    }
}
