package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.DatabaseConnectorManager;
import com.globallogic.orchestrator.connector.database.NodeDatabaseConnectorImpl;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseNodeDAOImpl implements NodeDAO {

    @Override
    @Transactional
    public void save(final Set<NodeDto> nodes) {
        NodeDatabaseConnectorImpl connector = new NodeDatabaseConnectorImpl();

        JdbcTemplate jdbcTemplate = DatabaseConnectorManager.getInstance().getJdbcTemplate();

        for (NodeDto el : nodes) {
            connector.insert(jdbcTemplate, el.getName(), getString(el.getRoles()));
        }
    }

    private String getString(Set<String> roles) {
        StringBuilder sb = new StringBuilder();
        roles.forEach(role -> sb.append(roles).append(SeparatorHolder.getSeparatorDatabaseString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    @Override
    public Set<NodeDto> load() {
        JdbcTemplate jdbcTemplate = DatabaseConnectorManager.getInstance().getJdbcTemplate();
        return new NodeDatabaseConnectorImpl().getAll(jdbcTemplate).stream().map(this::extract)
                    .collect(Collectors.toSet());
    }

    private NodeDto extract(final String... params) {
        NodeDto node = new NodeDto();
        node.setName(params[0]);
        node.setRoles(new HashSet<>(Arrays.asList(params[1].split(SeparatorHolder.getSeparatorDatabaseString()))));
        return node;
    }
}
