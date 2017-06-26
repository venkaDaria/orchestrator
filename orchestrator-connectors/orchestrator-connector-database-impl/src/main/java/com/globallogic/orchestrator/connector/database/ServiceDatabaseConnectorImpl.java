package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class ServiceDatabaseConnectorImpl extends AbstractDatabaseConnector implements ServiceDatabaseConnector {

    private static final String INSERT_SERVICE_QUERY = "INSERT INTO services VALUES(?,?)";

    private static final String INSERT_ROLE_QUERY = "INSERT INTO roles VALUES(?) ON DUPLICATE KEY UPDATE value = value";

    private static final String INSERT_SERVICE_ROLES_QUERY = "INSERT INTO service_roles VALUES(DEFAULT,?,?)";

    private static final String INSERT_PORT_QUERY = "INSERT INTO ports VALUES(?,?) ON DUPLICATE KEY UPDATE value = value";

    private static final String INSERT_VOLUME_QUERY = "INSERT INTO volumes VALUES(?,?) ON DUPLICATE KEY UPDATE value = value";

    private static final String GET_ALL_SERVICES_QUERY = "select distinct services.name, services.image, " +
            "service_roles.value, ports.value, volumes.value from services " +
            "left join  service_roles on service_roles.service =  services.name " +
            "left join roles on service_roles.value = roles.value " +
            "left join volumes on volumes.service = services.name " +
            "left join ports on ports.service = services.name;";

    private static final String GET_SERVICE_QUERY = "select distinct services.name, services.image, " +
            "service_roles.value, ports.value, volumes.value from services " +
            "left join  service_roles on service_roles.service =  services.name " +
            "left join roles on service_roles.value = roles.value " +
            "left join volumes on volumes.service = services.name " +
            "left join ports on ports.service = services.name WHERE name = ?";


    @Override
    public void insert(final String name, final String image, final Set<String> roles, final Set<String> ports, final Set<String> volumes) {
        super.insert(INSERT_SERVICE_QUERY, name, image);
        roles.forEach(role -> {
            super.insert(INSERT_ROLE_QUERY, role);
            super.insert(INSERT_SERVICE_ROLES_QUERY, role, name);
        });
        ports.forEach(port -> super.insert(INSERT_PORT_QUERY, port, name));
        volumes.forEach(volume -> super.insert(INSERT_VOLUME_QUERY, volume, name));
    }

    @Override
    public <T> Set<T> getAll(final RowMapper<T> rowMapper) {
        return getAll(GET_ALL_SERVICES_QUERY, rowMapper);
    }

    @Override
    public <T> T getByName(final String name, final RowMapper<T> rowMapper) {
        return get(GET_SERVICE_QUERY, name, rowMapper);
    }
}
