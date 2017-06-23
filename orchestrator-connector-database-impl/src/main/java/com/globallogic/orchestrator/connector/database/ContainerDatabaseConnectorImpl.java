package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.database.AbstractDatabaseConnector;
import com.globallogic.orchestrator.connector.database.ContainerDatabaseConnector;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Repository
public class ContainerDatabaseConnectorImpl extends AbstractDatabaseConnector implements ContainerDatabaseConnector {

    private static final String INSERT_CONTAINER_QUERY = "INSERT INTO containers VALUES(?,?,?,?)";

    private static final String GET_ALL_CONTAINERS_QUERY = "SELECT * FROM containers";

    private static final String GET_CONTAINER_QUERY = "SELECT * FROM containers WHERE id = ?";

    @Override
    public void insert(final String... params) {
        validate(4, "container", params);
        insert(INSERT_CONTAINER_QUERY, params);
    }

    @Override
    public Set<String[]> getAll() {
        return getAll(GET_ALL_CONTAINERS_QUERY);
    }

    @Override
    public String[] getById(String id) {
        return get(GET_CONTAINER_QUERY, id);
    }

    @Override
    public String[] extract(final ResultSet rs, int rowNum) throws SQLException {
        return new String[]{
                rs.getString("id"),
                rs.getString("status"),
                rs.getString("node"),
                rs.getString("service")
        };
    }
}
