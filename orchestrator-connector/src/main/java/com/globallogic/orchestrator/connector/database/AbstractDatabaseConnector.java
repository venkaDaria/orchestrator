package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    protected Set<String[]> getAll(final String query) {
        Set<String[]> set = new HashSet<>();
        jdbcTemplate.query(query, (RowCallbackHandler) rs -> set.add(extract(rs)));
        return set;
    }

    protected abstract String[] extract(final ResultSet rs) throws SQLException;
}
