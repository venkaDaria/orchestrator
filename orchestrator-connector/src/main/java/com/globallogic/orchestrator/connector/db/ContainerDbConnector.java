package com.globallogic.orchestrator.connector.db;

import com.globallogic.orchestrator.connector.exception.DbException;
import com.globallogic.orchestrator.dto.ContainerDTO;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ContainerDbConnector extends DbConnector<ContainerDTO> {

    private static final String INSERT_CONTAINER = "insert into containers values(?,?,?,?)";
    private static final String GET_CONTAINERS = "select * from containers";

    @Override
    public void insert(Set<ContainerDTO> set) throws DbException {
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (ContainerDTO el : set) {
                insert(con, el);
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            throw new DbException("Can't insert containers", e);
        } finally {
            close(con);
        }
    }

    private void insert(Connection con, ContainerDTO container) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(INSERT_CONTAINER);
            int k = 1;
            pstmt.setString(k++, container.getId());
            pstmt.setString(k++, container.getStatus());
            pstmt.setString(k++, container.getNodeName());
            pstmt.setString(k, container.getServiceName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't insert container", e);
        } finally {
            close(pstmt);
        }
    }

    @Override
    public Set<ContainerDTO> getAll() throws DbException {
        Set<ContainerDTO> containers;
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            containers = getAll(con);
        } catch (SQLException e) {
            throw new DbException("Cannot obtain containers", e);
        } finally {
            close(con);
        }
        return containers;
    }

    private Set<ContainerDTO> getAll(Connection con) throws SQLException {
        Set<ContainerDTO> containers = new HashSet<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(GET_CONTAINERS);
            while (rs.next()) {
                containers.add(extractContainer(rs));
            }
        } finally {
            close(stmt);
            close(rs);
        }
        return containers;
    }

    private static ContainerDTO extractContainer(ResultSet rs) throws SQLException {
        ContainerDTO container = new ContainerDTO();
        container.setId(rs.getString("id"));
        container.setStatus(rs.getString("status"));
        container.setNodeName(rs.getString("node"));
        container.setServiceName(rs.getString("service"));
        return container;
    }
}
