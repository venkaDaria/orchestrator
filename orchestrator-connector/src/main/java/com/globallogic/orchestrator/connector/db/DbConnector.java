package com.globallogic.orchestrator.connector.db;

import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public abstract class DbConnector {

    public abstract void insert(Connection con, String... params);

    public abstract Set<String[]> getAll(Connection con) throws SQLException;

    protected abstract String[] extract(ResultSet rs) throws SQLException;

    protected void insert(Connection con, String query, String... params) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(query);
            for (int k = 0; k < params.length; k++) {
                pstmt.setString(k + 1, params[k]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Can't insert this object", e);
        } finally {
            close(pstmt);
        }
    }

    protected Set<String[]> getAll(Connection con, String query) throws SQLException {
        Set<String[]> containers = new HashSet<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                containers.add(extract(rs));
            }
        } finally {
            close(stmt);
            close(rs);
        }
        return containers;
    }

    protected void close(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DatabaseOperationException("Can't close result set");
            }
        }
    }

    protected void close(final Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                throw new DatabaseOperationException("Can't close statement");
            }
        }
    }
}
