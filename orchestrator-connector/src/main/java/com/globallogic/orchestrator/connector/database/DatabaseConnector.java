package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public interface DatabaseConnector {

    void insert(final JdbcTemplate jdbcTemplate, final String... params);

    Set<String[]> getAll(final JdbcTemplate jdbcTemplate);

    String[] extract(final ResultSet rs) throws SQLException;

}
