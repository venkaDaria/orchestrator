package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.DatabaseConnectorManager;
import com.globallogic.orchestrator.connector.database.NodeDatabaseConnectorImpl;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseNodeDAOImpl extends DatabaseDAOConnector implements NodeDAO {

    @Override
    public void save(final Set<NodeDto> nodes) {
        Connection con = null;
        NodeDatabaseConnectorImpl connector = new NodeDatabaseConnectorImpl();
        try {
            con = DatabaseConnectorManager.getInstance().getConnection();
            con.setAutoCommit(false);
            for (NodeDto el : nodes) {
                connector.insert(con, el.getName(), getString(el.getRoles()));
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            throw new DatabaseOperationException("Can't insert nodes", e);
        } finally {
            close(con);
        }
    }

    private String getString(Set<String> roles) {
        StringBuilder sb = new StringBuilder();
        roles.forEach(role -> sb.append(roles).append(SeparatorHolder.getSeparatorDatabaseString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    @Override
    public Set<NodeDto> load() {
        Set<NodeDto> nodes;
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

    private NodeDto extract(final String... params) {
        NodeDto node = new NodeDto();
        node.setName(params[0]);
        node.setRoles(new HashSet<>(Arrays.asList(params[1].split(SeparatorHolder.getSeparatorDatabaseString()))));
        return node;
    }
}
