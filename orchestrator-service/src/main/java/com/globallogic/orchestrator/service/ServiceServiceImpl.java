package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.interfaces.ServiceService;
import com.globallogic.orchestrator.service.translators.ServiceDtoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Qualifier("getServiceDAO")
    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private ServiceDtoTranslator translator;

    @Override
    public void save(final Set<Service> services) {
        serviceDAO.save(services.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<Service> load() {
        return serviceDAO.load().stream().map(translator::fromDto).collect(Collectors.toSet());
    }
}
