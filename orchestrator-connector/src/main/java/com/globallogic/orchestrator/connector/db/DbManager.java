package com.globallogic.orchestrator.connector.db;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static final String URL = "jdbc:mysql://localhost:3306/config?user=root&password=yroot";

    private static DbManager instance;

    public synchronized static DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    private DbManager() { }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Can't get connection", e);
        }
    }
}
