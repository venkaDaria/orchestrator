package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dto.ContainerDTO;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.interfaces.ContainerService;

import java.util.Set;

public class ContainerServiceImpl implements ContainerService {

    @Override
    public void save(final Set<Container> containers) {
        DAOFactory.getDAOFactory().getContainerDAO().save(containers);
    }

    @Override
    public Set<ContainerDTO> load() {
        return DAOFactory.getDAOFactory().getContainerDAO().load();
    }
}
