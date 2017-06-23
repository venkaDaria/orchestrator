package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.RowMapper;

import java.util.Set;

public interface NodeDatabaseConnector {

    void insert(final String... params);

    <T> Set<T> getAll(RowMapper<T> rowMapper);

    <T> T getByName(String name, RowMapper<T> rowMapper);
}
