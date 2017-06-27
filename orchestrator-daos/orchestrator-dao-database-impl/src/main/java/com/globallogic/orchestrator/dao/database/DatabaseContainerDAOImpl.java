package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ContainerDatabaseConnector;
import com.globallogic.orchestrator.dao.database.mapper.ContainerRowMapper;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class DatabaseContainerDAOImpl implements DatabaseContainerDAO {

    @Autowired
    private ContainerDatabaseConnector connector;

    @Autowired
    private ContainerRowMapper mapper;

    @Override
    @Transactional
    public void save(final Set<ContainerDto> containers) {
        containers.forEach(el -> connector.insert(el.getId(), el.getStatus(), el.getNodeName(), el.getServiceName()));
    }

    @Override
    public Set<ContainerDto> load() {
        return connector.getAll(mapper);
    }

    @Override
    public ContainerDto getById(final String id) {
        return connector.getById(id, mapper);
    }
}
