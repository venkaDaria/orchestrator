package com.globallogic.orchestrator.connector.db;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ContainerDbConnector extends DbConnector {
    private static final String INSERT_CONTAINER_QUERY = "insert into containers values(?,?,?,?)";
    private static final String GET_ALL_CONTAINERS_QUERY = "select * from containers";

    @Override
    public void insert(final Connection con, final String... params) {
        if (params.length != 4) {
            throw new DatabaseOperationException("Can't insert container");
        }
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
