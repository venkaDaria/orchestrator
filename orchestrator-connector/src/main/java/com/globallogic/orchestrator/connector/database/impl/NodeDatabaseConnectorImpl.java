package com.globallogic.orchestrator.connector.database.impl;

import com.globallogic.orchestrator.connector.database.AbstractDatabaseConnector;
import com.globallogic.orchestrator.connector.database.NodeDatabaseConnector;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Repository
public class NodeDatabaseConnectorImpl extends AbstractDatabaseConnector implements NodeDatabaseConnector {

    private static final String INSERT_NODE_QUERY = "INSERT INTO nodes VALUES(?,?)";

    private static final String GET_ALL_NODES_QUERY = "SELECT * FROM nodes";

    @Override
    public void insert(final String... params) {
        validate(2, "node", params);
        insert(INSERT_NODE_QUERY, params);
    }

    @Override
    public Set<String[]> getAll() {
        return getAll(GET_ALL_NODES_QUERY);
    }

    @Override
    public String[] extract(final ResultSet rs) throws SQLException {
        return new String[]{
                rs.getString("name"),
                rs.getString("roles")
        };
    }
}
