package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.interfaces.ServiceService;
import com.globallogic.orchestrator.service.translators.ServiceDtoTranslator;

import java.util.Set;
import java.util.stream.Collectors;

public class ServiceServiceImpl implements ServiceService {

    private DAOType type;
    private ServiceDtoTranslator translator;

    public ServiceServiceImpl(final DAOType type) {
        this.type = type;
        translator = new ServiceDtoTranslator();
    }

    @Override
    public void save(final Set<Service> services) {
        DAOFactory.getInstance(type).getServiceDAO().save(services.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<Service> load() {
        return DAOFactory.getInstance(type).getServiceDAO().load().stream().map(translator::fromDto).collect(Collectors.toSet());
    }
}
