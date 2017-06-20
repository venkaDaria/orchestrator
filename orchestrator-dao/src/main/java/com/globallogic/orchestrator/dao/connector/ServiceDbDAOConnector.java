package com.globallogic.orchestrator.dao.connector;

import com.globallogic.orchestrator.connector.db.DbManager;
import com.globallogic.orchestrator.connector.db.ServiceDbConnector;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.dto.ServiceDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

public class ServiceDbDAOConnector extends DbDAOConnector<ServiceDTO> {
    public void insert(Set<ServiceDTO> set) throws DatabaseOperationException {
        Connection con = null;
        ServiceDbConnector connector = new ServiceDbConnector();
        try {
            con = DbManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (ServiceDTO el : set) {
                connector.insert(con, el.getName(), el.getImage(), el.getRoles(), el.getPorts(), el.getVolumes());
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            throw new DatabaseOperationException("Can't insert services", e);
        } finally {
            close(con);
        }
    }


    @Override
    public Set<ServiceDTO> getAll() throws DatabaseOperationException {
        Set<ServiceDTO> services;
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            services = new ServiceDbConnector().getAll(con).stream().map(ServiceDbDAOConnector::extract)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Cannot obtain services", e);
        } finally {
            close(con);
        }
        return services;
    }

    private static ServiceDTO extract(String... params) {
        if (params.length != 5) {
            throw new DatabaseOperationException("Can't extract node");
        }
        ServiceDTO service = new ServiceDTO();
        service.setName(params[0]);
        service.setImage(params[1]);
        service.setRoles(params[2]);
        service.setPorts(params[3]);
        service.setVolumes(params[4]);
        return service;
    }
}
