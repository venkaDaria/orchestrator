package com.globallogic.orchestrator.connector.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class NodeDatabaseConnectorImpl extends AbstractDatabaseConnector implements NodeDatabaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(NodeDatabaseConnectorImpl.class);

    private static final String INSERT_NODE_QUERY = "INSERT INTO nodes VALUES(?)";

    private static final String INSERT_ROLE_QUERY = "INSERT INTO roles VALUES(?) ON DUPLICATE KEY UPDATE value = value";

    private static final String INSERT_NODE_ROLES_QUERY = "INSERT INTO node_roles VALUES(DEFAULT,?,?)";

    private static final String GET_ALL_NODES_QUERY = "select distinct nodes.name, node_roles.value from nodes " +
            "left join node_roles on node_roles.node = nodes.name left join roles on node_roles.value = roles.value;";

    private static final String GET_NODE_QUERY = "select distinct nodes.name, node_roles.value from nodes " +
            "left join node_roles on node_roles.node = nodes.name " +
            "left join roles on node_roles.value = roles.value WHERE name = ?";

    @Override
    @Transactional
    public void insert(final String name, final Set<String> roles) {
        insert(INSERT_NODE_QUERY, name);
        roles.forEach(role -> {
            super.insert(INSERT_ROLE_QUERY, role);
            super.insert(INSERT_NODE_ROLES_QUERY, role, name);
        });
        LOG.debug("Insert a node -> " + name + ", " + roles);
    }

    @Override
    public <T> Set<T> getAll(final RowMapper<T> rowMapper) {
        LOG.debug("Get all nodes");
        return getAll(GET_ALL_NODES_QUERY, rowMapper);
    }

    @Override
    public <T> T getByName(final String name, final RowMapper<T> rowMapper) {
        LOG.debug("Get a node by name -> " + name);
        return get(GET_NODE_QUERY, name, rowMapper);
    }
}
