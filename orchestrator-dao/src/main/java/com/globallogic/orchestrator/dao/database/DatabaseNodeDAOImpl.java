package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.DatabaseConnectorManager;
import com.globallogic.orchestrator.connector.database.NodeDatabaseConnectorImpl;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.dto.NodeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseNodeDAOImpl extends DatabaseDAOConnector<NodeDTO> implements NodeDAO {

    @Override
    public void save(final Set<NodeDTO> nodes) {
        Connection con = null;
        NodeDatabaseConnectorImpl connector = new NodeDatabaseConnectorImpl();
        try {
            con = DatabaseConnectorManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (NodeDTO el : nodes) {
                connector.insert(con, el.getName(), el.getRoles());
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            throw new DatabaseOperationException("Can't insert nodes", e);
        } finally {
            close(con);
        }
    }

    @Override
    public Set<NodeDTO> load() {
        Set<NodeDTO> nodes;
        Connection con = null;
        try {
            con = DatabaseConnectorManager.getInstance().getConnection();
            nodes = new NodeDatabaseConnectorImpl().getAll(con).stream().map(this::extract)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Cannot obtain nodes", e);
        } finally {
            close(con);
        }
        return nodes;
    }

    private NodeDTO extract(final String... params) {
        if (params.length != 2) {
            throw new DatabaseOperationException("Can't extract node");
        }
        NodeDTO node = new NodeDTO();
        node.setName(params[0]);
        node.setRoles(params[1]);
        return node;
    }
}
