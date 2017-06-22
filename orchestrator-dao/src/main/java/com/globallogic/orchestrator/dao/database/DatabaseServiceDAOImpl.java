package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.DatabaseConnectorManager;
import com.globallogic.orchestrator.connector.database.ServiceDatabaseConnectorImpl;
import com.globallogic.orchestrator.connector.exception.DatabaseOperationException;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseServiceDAOImpl implements ServiceDAO {

    @Override
    @Transactional
    public void save(final Set<ServiceDto> services) {
        ServiceDatabaseConnectorImpl connector = new ServiceDatabaseConnectorImpl();

        JdbcTemplate jdbcTemplate = DatabaseConnectorManager.getInstance().getJdbcTemplate();

        for (ServiceDto el : services) {
            connector.insert(jdbcTemplate, el.getName(), el.getImage(), getString(el.getRoles()),
                    getString(el.getPorts()), getString(el.getVolumes()));
        }
    }

    private String getString(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        set.forEach(el -> sb.append(el).append(SeparatorHolder.getSeparatorDatabaseString()));
        return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

    @Override
    public Set<ServiceDto> load() {
        JdbcTemplate jdbcTemplate = DatabaseConnectorManager.getInstance().getJdbcTemplate();
        return new ServiceDatabaseConnectorImpl().getAll(jdbcTemplate).stream().map(this::extract)
                .collect(Collectors.toSet());
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
