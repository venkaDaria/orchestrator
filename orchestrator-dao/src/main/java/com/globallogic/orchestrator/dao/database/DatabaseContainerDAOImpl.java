package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.connector.database.ContainerDatabaseConnector;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class DatabaseContainerDAOImpl implements DatabaseContainerDAO {

    @Autowired
    private ContainerDatabaseConnector connector;

    @Override
    @Transactional
    public void save(final Set<ContainerDto> containers) {
        containers.forEach(el -> connector.insert(el.getId(), el.getStatus(), el.getNodeName(), el.getServiceName()));
    }

    @Override
    public Set<ContainerDto> load() {
        return connector.getAll().stream().map(this::extract).collect(Collectors.toSet());
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
