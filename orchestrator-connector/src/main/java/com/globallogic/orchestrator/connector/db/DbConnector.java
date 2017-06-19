package com.globallogic.orchestrator.connector.db;

import com.globallogic.orchestrator.connector.exception.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public abstract class DbConnector<T> {
    public abstract void insert(Set<T> set) throws DbException;

    public abstract Set<T> getAll() throws DbException;

    protected void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DbException("Can't rollback");
            }
        }
    }

    protected void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new DbException("Can't close connection");
            }
        }
    }

    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DbException("Can't close result set");
            }
        }
    }

    protected void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                throw new DbException("Can't close statement");
            }
        }
    }
}
