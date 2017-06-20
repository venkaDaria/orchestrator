package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ContainerDatabaseConnectorImpl;
import com.globallogic.orchestrator.connector.database.DatabaseConnectorManager;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseContainerDAOImpl extends DatabaseDAOConnector<ContainerDTO> implements ContainerDAO {

    @Override
    public void save(final Set<ContainerDTO> containers) {
        Connection con = null;
        ContainerDatabaseConnectorImpl connector = new ContainerDatabaseConnectorImpl();
        try {
            con = DatabaseConnectorManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (ContainerDTO el : containers) {
                connector.insert(con, el.getId(), el.getStatus(), el.getNodeName(), el.getServiceName());
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            throw new DatabaseOperationException("Can't insert containers", e);
        } finally {
            close(con);
        }
    }

    @Override
    public Set<ContainerDTO> load() {
        Set<ContainerDTO> containers;
        Connection con = null;
        try {
            con = DatabaseConnectorManager.getInstance().getConnection();
            containers = new ContainerDatabaseConnectorImpl().getAll(con).stream().map(this::extract)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Cannot obtain containers", e);
        } finally {
            close(con);
        }
        return containers;
    }

    private ContainerDTO extract(final String... params) {
        if (params.length != 4) {
            throw new DatabaseOperationException("Can't extract container");
        }
        ContainerDTO container = new ContainerDTO();
        container.setId(params[0]);
        container.setStatus(params[1]);
        container.setNodeName(params[2]);
        container.setServiceName(params[3]);
        return container;
    }
}
