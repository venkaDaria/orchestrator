package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOSystem;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.interfaces.ContainerService;

import java.util.Set;

public class ContainerServiceImpl implements ContainerService {
    private DAOSystem system;

    public ContainerServiceImpl(final DAOSystem system) {
        this.system = system;
    }

    @Override
    public void save(final Set<Container> containers) {
        DAOFactory.getInstance(system).getContainerDAO().save(containers);
    }

    @Override
    public Set<ContainerDTO> load() {
        return DAOFactory.getInstance(system).getContainerDAO().load();
    }
}
