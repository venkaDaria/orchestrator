package com.globallogic.orchestrator.dao.connector;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public abstract class DbDAOConnector<T> {
    public abstract void insert(final Set<T> set);

    public abstract Set<T> getAll();

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
