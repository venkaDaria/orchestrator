package com.globallogic.orchestrator.connector.db;

import com.globallogic.orchestrator.connector.exception.DbException;
import com.globallogic.orchestrator.dto.NodeDTO;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class NodeDbConnector extends DbConnector<NodeDTO> {
    private static final String INSERT_NODE = "insert into nodes values(?,?)";
    private static final String GET_NODES = "select * from nodes";

    @Override
    public void insert(Set<NodeDTO> set) throws DbException {
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (NodeDTO el : set) {
                insert(con, el);
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            throw new DbException("Can't insert nodes", e);
        } finally {
            close(con);
        }
    }

    private void insert(Connection con, NodeDTO node) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(INSERT_NODE);
            int k = 1;
            pstmt.setString(k++, node.getName());
            pstmt.setString(k, node.getRoles());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't insert node", e);
        } finally {
            close(pstmt);
        }
    }

    @Override
    public Set<NodeDTO> getAll() throws DbException {
        Set<NodeDTO> nodes;
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            nodes = getAll(con);
        } catch (SQLException e) {
            throw new DbException("Cannot obtain nodes", e);
        } finally {
            close(con);
        }
        return nodes;
    }

    private Set<NodeDTO> getAll(Connection con) throws SQLException {
        Set<NodeDTO> nodes = new HashSet<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(GET_NODES);
            while (rs.next()) {
                nodes.add(extractNode(rs));
            }
        } finally {
            close(stmt);
            close(rs);
        }
        return nodes;
    }

    private static NodeDTO extractNode(ResultSet rs) throws SQLException {
        NodeDTO node = new NodeDTO();
        node.setName(rs.getString("name"));
        node.setRoles(rs.getString("roles"));
        return node;
    }
}
