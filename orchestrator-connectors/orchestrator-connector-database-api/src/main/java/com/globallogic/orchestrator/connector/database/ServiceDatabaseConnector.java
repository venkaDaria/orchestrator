package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.RowMapper;

import java.util.Set;

public interface ServiceDatabaseConnector {

    void insert(final String name, final String image, final Set<String> roles, final Set<String> ports, final Set<String> volumes);

    <T> Set<T> getAll(final RowMapper<T> rowMapper);

    <T> T getByName(final String name, final RowMapper<T> rowMapper);
}
