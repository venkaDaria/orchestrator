package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.DatabaseConnectorManager;
import com.globallogic.orchestrator.connector.database.ServiceDatabaseConnectorImpl;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dao.dto.ServiceDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseServiceDAOImpl extends DatabaseDAOConnector<ServiceDTO> implements ServiceDAO {

    @Override
    public void save(final Set<ServiceDTO> services) {
        Connection con = null;
        ServiceDatabaseConnectorImpl connector = new ServiceDatabaseConnectorImpl();
        try {
            con = DatabaseConnectorManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (ServiceDTO el : services) {
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
    public Set<ServiceDTO> load() {
        Set<ServiceDTO> services;
        Connection con = null;
        try {
            con = DatabaseConnectorManager.getInstance().getConnection();
            services = new ServiceDatabaseConnectorImpl().getAll(con).stream().map(this::extract)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Cannot obtain services", e);
        } finally {
            close(con);
        }
        return services;
    }

    private ServiceDTO extract(final String... params) {
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
