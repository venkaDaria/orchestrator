package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public abstract class AbstractDatabaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDatabaseConnector.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected void validate(final int len, final String name, final String... params) {
        if (params.length != len) {
            throw new DatabaseOperationException("Can't insert " + name);
        }
        LOG.debug("Validation is successful");
    }

    protected void insert(final String query, final String... params) {
        jdbcTemplate.update(query, (Object[]) params);
        LOG.debug("Insert an object");
    }

    protected <T> Set<T> getAll(final String query, final RowMapper<T> mapper) {
        LOG.debug("Get all objects");
        return new HashSet<>(jdbcTemplate.query(query, mapper));
    }

    protected <T> T get(final String query, final String param, final RowMapper<T> mapper) {
        LOG.debug("Get an object");
        return jdbcTemplate.query(query, mapper, param).get(0);
    }
}
