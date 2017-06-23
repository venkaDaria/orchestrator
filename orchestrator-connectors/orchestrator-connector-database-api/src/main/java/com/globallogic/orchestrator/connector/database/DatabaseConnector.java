package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.RowMapper;

import java.util.Set;

public interface DatabaseConnector {

    void insert(final String... params);

    <T> Set<T> getAll(final RowMapper<T> rowMapper);
}
