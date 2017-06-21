package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectorManager {
    private static final String URL = "jdbc:mysql://localhost:3306/config?user=root&password=yroot";

    private DatabaseConnectorManager() {
    }

    private static class DatabaseConnectorManagerHolder {
        private static final DatabaseConnectorManager INSTANCE = new DatabaseConnectorManager();
    }

    public synchronized static DatabaseConnectorManager getInstance() {
        return DatabaseConnectorManagerHolder.INSTANCE;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Can't get connection", e);
        }
    }
}