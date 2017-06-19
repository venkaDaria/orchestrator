package com.globallogic.orchestrator.connector.db;

import com.globallogic.orchestrator.connector.exception.DbException;
import com.globallogic.orchestrator.dto.ServiceDTO;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceDbConnector extends DbConnector<ServiceDTO>{
    private static final String INSERT_SERVICE = "insert into services values(?,?,?,?,?)";
    private static final String GET_SERVICES = "select * from services";

    @Override
    public void insert(Set<ServiceDTO> set) throws DbException {
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (ServiceDTO el : set) {
                insert(con, el);
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            throw new DbException("Can't insert services", e);
        } finally {
            close(con);
        }
    }

    private void insert(Connection con, ServiceDTO service) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(INSERT_SERVICE);
            int k = 1;
            pstmt.setString(k++, service.getName());
            pstmt.setString(k++, service.getImage());
            pstmt.setString(k++, service.getRoles());
            pstmt.setString(k++, service.getPorts());
            pstmt.setString(k, service.getVolumes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't insert service", e);
        } finally {
            close(pstmt);
        }
    }

    @Override
    public Set<ServiceDTO> getAll() throws DbException {
        Set<ServiceDTO> services;
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            services = getAll(con);
        } catch (SQLException e) {
            throw new DbException("Cannot obtain services", e);
        } finally {
            close(con);
        }
        return services;
    }

    private Set<ServiceDTO> getAll(Connection con) throws SQLException {
        Set<ServiceDTO> services = new HashSet<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(GET_SERVICES);
            while (rs.next()) {
                services.add(extractService(rs));
            }
        } finally {
            close(stmt);
            close(rs);
        }
        return services;
    }

    private static ServiceDTO extractService(ResultSet rs) throws SQLException {
        ServiceDTO service = new ServiceDTO();
        service.setName(rs.getString("name"));
        service.setImage(rs.getString("image"));
        service.setRoles(rs.getString("roles"));
        service.setPorts(rs.getString("ports"));
        service.setVolumes(rs.getString("volumes"));
        return service;
    }
}
