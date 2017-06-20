package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOSystem;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.interfaces.ServiceService;

import java.util.Set;

public class ServiceServiceImpl implements ServiceService {

    private DAOSystem system;

    public ServiceServiceImpl(final DAOSystem system) {
        this.system = system;
    }

    @Override
    public void save(final Set<Service> services) {
        DAOFactory.getInstance(system).getServiceDAO().save(services);
    }

    @Override
    public Set<Service> load() {
        return DAOFactory.getInstance(system).getServiceDAO().load();
    }
}
