package com.globallogic.orchestrator.connector.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseConnectorManager {
    private JdbcTemplate jdbcTemplate;

    private DatabaseConnectorManager() {
    }

    public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    private static class DatabaseConnectorManagerHolder {
        private static final DatabaseConnectorManager INSTANCE = new DatabaseConnectorManager();
    }

    public synchronized static DatabaseConnectorManager getInstance() {
        return DatabaseConnectorManagerHolder.INSTANCE;
    }
}