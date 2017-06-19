package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.interfaces.ServiceService;

import java.util.Set;

public class ServiceServiceImpl implements ServiceService {
    @Override
    public void save(final Set<Service> services) {
        DAOFactory.getDAOFactory().getServiceDAO().save(services);
    }

    @Override
    public Set<Service> load() {
        return DAOFactory.getDAOFactory().getServiceDAO().load();
    }
}
