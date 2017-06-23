package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.database.AbstractDatabaseConnector;
import com.globallogic.orchestrator.connector.database.ServiceDatabaseConnector;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Set<String[]> getAll()  {
        return getAll(GET_ALL_SERVICES_QUERY);
    }

    @Override
    public String[] getByName(String name) {
        return get(GET_SERVICE_QUERY, name);
    }

    @Override
    public String[] extract(final ResultSet rs, int rowNum) throws SQLException {
        return new String[]{
                rs.getString("name"),
                rs.getString("image"),
                rs.getString("roles"),
                rs.getString("ports"),
                rs.getString("volumes")
        };
    }
}
