package com.globallogic.orchestrator.connector.db;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class NodeDbConnector extends DbConnector {
    private static final String INSERT_NODE_QUERY = "insert into nodes values(?,?)";
    private static final String GET_ALL_NODES_QUERY = "select * from nodes";

    @Override
    public void insert(Connection con, String... params) {
        if (params.length != 2) {
            throw new DatabaseOperationException("Can't insert node");
        }
        insert(con, INSERT_NODE_QUERY, params);
    }

    @Override
    public Set<String[]> getAll(Connection con) throws SQLException {
        return getAll(con, GET_ALL_NODES_QUERY);
    }

    @Override
    protected String[] extract(ResultSet rs) throws SQLException {
        return new String[]{
            rs.getString("name"),
            rs.getString("roles")
        };
    }
}
