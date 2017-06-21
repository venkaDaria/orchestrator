package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.interfaces.ContainerService;

import java.util.HashSet;
import java.util.Set;

public class ContainerServiceImpl implements ContainerService {
    private DAOType type;

    public ContainerServiceImpl(final DAOType type) {
        this.type = type;
    }

    @Override
    public void save(final Set<Container> containers) {
        Set<ContainerDTO> set = new HashSet<>();
        containers.forEach(container -> set.add(getDTO(container)));

        DAOFactory.getInstance(type).getContainerDAO().save(set);
    }

    @Override
    public Set<ContainerDTO> load() {
        return DAOFactory.getInstance(type).getContainerDAO().load();
    }

    private ContainerDTO getDTO(final Container container) {
        ContainerDTO dto = new ContainerDTO();

        dto.setId(container.getId());
        dto.setNodeName(container.getNode().getName());
        dto.setServiceName(container.getService().getName());
        dto.setStatus(container.getStatus().toString());

        return dto;
    }
}
