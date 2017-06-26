package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.RowMapper;

import java.util.Set;

public interface NodeDatabaseConnector {

    void insert(String name, Set<String> roles);

    <T> Set<T> getAll(final RowMapper<T> rowMapper);

    <T> T getByName(final String name, final RowMapper<T> rowMapper);
}
