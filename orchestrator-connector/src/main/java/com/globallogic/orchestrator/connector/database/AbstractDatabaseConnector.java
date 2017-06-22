package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDatabaseConnector implements DatabaseConnector {

    protected void validate(int len, String name, String... params) {
        if (params.length != len) {
            throw new DatabaseOperationException("Can't insert " + name);
        }
    }

    protected void insert(final JdbcTemplate jdbcTemplate, final String query, final String... params) {
        jdbcTemplate.update(query, (Object[]) params);
    }

    protected Set<String[]> getAll(final JdbcTemplate jdbcTemplate, final String query) {
        Set<String[]> containers = new HashSet<>();
        jdbcTemplate.query(query, (RowCallbackHandler) rs -> containers.add(extract(rs)));
        return containers;
    }
}
