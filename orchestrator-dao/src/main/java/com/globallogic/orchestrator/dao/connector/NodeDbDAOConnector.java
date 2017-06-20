package com.globallogic.orchestrator.dao.connector;

import com.globallogic.orchestrator.connector.db.DbManager;
import com.globallogic.orchestrator.connector.db.NodeDbConnector;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.dto.NodeDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

public class NodeDbDAOConnector extends DbDAOConnector<NodeDTO> {

    public void insert(final Set<NodeDTO> set) {
        Connection con = null;
        NodeDbConnector connector = new NodeDbConnector();
        try {
            con = DbManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (NodeDTO el : set) {
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
    public Set<NodeDTO> getAll() {
        Set<NodeDTO> nodes;
        Connection con = null;
        try {
            con = DbManager.getInstance().getConnection();
            nodes = new NodeDbConnector().getAll(con).stream().map(NodeDbDAOConnector::extract)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            throw new DatabaseOperationException("Cannot obtain nodes", e);
        } finally {
            close(con);
        }
        return nodes;
    }

    private static NodeDTO extract(final String... params) {
        if (params.length != 2) {
            throw new DatabaseOperationException("Can't extract node");
        }
        NodeDTO node = new NodeDTO();
        node.setName(params[0]);
        node.setRoles(params[1]);
        return node;
    }
}
