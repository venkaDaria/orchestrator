package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOSystem;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.interfaces.ContainerService;

import java.util.HashSet;
import java.util.Set;

public class ContainerServiceImpl implements ContainerService {
    private DAOSystem system;

    public ContainerServiceImpl(final DAOSystem system) {
        this.system = system;
    }

    @Override
    public void save(final Set<Container> containers) {
        Set<ContainerDTO> set = new HashSet<>();
        containers.forEach(container -> set.add(getDTO(container)));

        DAOFactory.getInstance(system).getContainerDAO().save(set);
    }

    @Override
    public Set<ContainerDTO> load() {
        return DAOFactory.getInstance(system).getContainerDAO().load();
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
