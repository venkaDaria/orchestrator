package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.dto.ServiceDTO;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.interfaces.ServiceService;
import com.globallogic.orchestrator.service.translators.ServiceTranslatorImpl;

import java.util.HashSet;
import java.util.Set;

public class ServiceServiceImpl implements ServiceService {

    private DAOType type;

    public ServiceServiceImpl(final DAOType type) {
        this.type = type;
    }

    @Override
    public void save(final Set<Service> services) {
        Set<ServiceDTO> set = new HashSet<>();

        ServiceTranslatorImpl translator = new ServiceTranslatorImpl();
        services.forEach(service -> set.add(translator.getDto(service)));

        DAOFactory.getInstance(type).getServiceDAO().save(set);
    }

    @Override
    public Set<Service> load() {
        Set<ServiceDTO> set = DAOFactory.getInstance(type).getServiceDAO().load();
        Set<Service> services = new HashSet<>();

        ServiceTranslatorImpl translator = new ServiceTranslatorImpl();
        set.forEach(dto -> services.add(translator.fromDto(dto)));

        return services;
    }
}
