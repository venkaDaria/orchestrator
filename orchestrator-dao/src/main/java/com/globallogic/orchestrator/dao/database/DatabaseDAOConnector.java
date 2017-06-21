package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseDAOConnector<T> {

    protected void rollback(final Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DatabaseOperationException("Can't rollback");
            }
        }
    }

    protected void close(final Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new DatabaseOperationException("Can't close connection");
            }
        }
    }
}
