package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.translators.ServiceDtoTranslator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

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

    @Override
    public Service getByName(final String name) {
        return translator.fromDto(serviceDAO.getByName(name));
    }

    @Override
    public void remove(final String name) {
        serviceDAO.remove(name);
    }

    @Override
    public void add(final String name, final String image, final List<String> roles, final List<String> ports, final List<String> volumes) {
        serviceDAO.add(name, image, roles, ports, volumes);
    }
}
