package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ContainerDatabaseConnectorImpl;
import com.globallogic.orchestrator.connector.database.DatabaseConnectorManager;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseContainerDAOImpl implements ContainerDAO {

    @Override
    @Transactional
    public void save(final Set<ContainerDto> containers) {
        ContainerDatabaseConnectorImpl connector = new ContainerDatabaseConnectorImpl();

        JdbcTemplate jdbcTemplate = DatabaseConnectorManager.getInstance().getJdbcTemplate();

        for (ContainerDto el : containers) {
            connector.insert(jdbcTemplate, el.getId(), el.getStatus(), el.getNodeName(), el.getServiceName());
        }
    }

    @Override
    public Set<ContainerDto> load() {
        JdbcTemplate jdbcTemplate = DatabaseConnectorManager.getInstance().getJdbcTemplate();
        return new ContainerDatabaseConnectorImpl().getAll(jdbcTemplate).stream().map(this::extract)
                    .collect(Collectors.toSet());
    }

    private ContainerDto extract(final String... params) {
        ContainerDto container = new ContainerDto();

        container.setId(params[0]);
        container.setStatus(params[1]);
        container.setNodeName(params[2]);
        container.setServiceName(params[3]);

        return container;
    }
}
