package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public abstract class AbstractDatabaseConnector implements DatabaseConnector {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected void validate(int len, String name, String... params) {
        if (params.length != len) {
            throw new DatabaseOperationException("Can't insert " + name);
        }
    }

    protected void insert(final String query, final String... params) {
        jdbcTemplate.update(query, (Object[]) params);
    }

    protected <T> Set<T> getAll(final String query, final RowMapper<T> mapper) {
        return new HashSet<>(jdbcTemplate.query(query, mapper));
    }

    protected <T> T get(final String query, String param, final RowMapper<T> mapper) {
        return jdbcTemplate.queryForObject(query, mapper, param);
    }
}
