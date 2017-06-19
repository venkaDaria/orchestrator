package com.globallogic.orchestrator.dao.db;

import com.globallogic.orchestrator.connector.db.ContainerDbConnector;
import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dto.ContainerDTO;
import com.globallogic.orchestrator.model.entity.Container;

import java.util.HashSet;
import java.util.Set;

public class DbContainerDAO implements ContainerDAO {

    @Override
    public void save(Set<Container> containers) {
        Set<ContainerDTO> set = new HashSet<>();
        containers.forEach(container -> set.add(getDTO(container)));

        new ContainerDbConnector().insert(set);
    }

    private static ContainerDTO getDTO(Container container) {
        ContainerDTO dto = new ContainerDTO();

        dto.setId(container.getId());
        dto.setNodeName(container.getNode().getName());
        dto.setServiceName(container.getService().getName());
        dto.setStatus(container.getStatus().toString());

        return dto;
    }

    @Override
    public Set<ContainerDTO> load() {
        return new ContainerDbConnector().getAll();
    }
}
