package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.DatabaseConnectorManager;
import com.globallogic.orchestrator.connector.database.ServiceDatabaseConnectorImpl;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dao.dto.ServiceDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseServiceDAOImpl extends DatabaseDAOConnector<ServiceDto> implements ServiceDAO {

    @Override
    public void save(final Set<ServiceDto> services) {
        Connection con = null;
        ServiceDatabaseConnectorImpl connector = new ServiceDatabaseConnectorImpl();
        try {
            con = DatabaseConnectorManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (ServiceDto el : services) {
                connector.insert(con, el.getName(), el.getImage(), getString(el.getRoles()),
                        getString(el.getPorts()), getString(el.getVolumes()));
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            throw new DatabaseOperationException("Can't insert services", e);
        } finally {
            close(con);
        }
    }

    private String getString(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        set.forEach(el -> sb.append(el).append(SeparatorHolder.getSeparatorDatabaseString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    @Override
    public Set<ServiceDto> load() {
        Set<ServiceDto> services;
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

    private ServiceDto extract(final String... params) {
        ServiceDto service = new ServiceDto();
        service.setName(params[0]);
        service.setImage(params[1]);
        service.setRoles(new HashSet<>(Arrays.asList(params[2].split(SeparatorHolder.getSeparatorDatabaseString()))));
        service.setPorts(new HashSet<>(Arrays.asList(params[3].split(SeparatorHolder.getSeparatorDatabaseString()))));
        service.setVolumes(new HashSet<>(Arrays.asList(params[4].split(SeparatorHolder.getSeparatorDatabaseString()))));
        return service;
    }
}
