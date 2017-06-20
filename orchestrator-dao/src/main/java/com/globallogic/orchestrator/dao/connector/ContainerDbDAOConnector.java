package com.globallogic.orchestrator.dao.connector;

import com.globallogic.orchestrator.connector.db.ContainerDbConnector;
import com.globallogic.orchestrator.connector.db.DbManager;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainerDbDAOConnector extends DbDAOConnector<ContainerDTO> {

    @Override
    public void insert(final Set<ContainerDTO> set) {
        Connection con = null;
        ContainerDbConnector connector = new ContainerDbConnector();
        try {
            con = DbManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (ContainerDTO el : set) {
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
    public Set<ContainerDTO> getAll() {
        Set<ContainerDTO> containers;
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            containers = new ContainerDbConnector().getAll(con).stream().map(ContainerDbDAOConnector::extract)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Cannot obtain containers", e);
        } finally {
            close(con);
        }
        return containers;
    }

    private static ContainerDTO extract(String... params) {
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
