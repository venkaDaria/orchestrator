package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.interfaces.ContainerService;
import com.globallogic.orchestrator.service.translators.ContainerDtoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private ContainerDAO containerDAO;

    @Autowired
    private ContainerDtoTranslator translator;

    @Override
    public void save(final Set<Container> containers) {
        containerDAO.save(containers.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<Container> load() {
        return containerDAO.load().stream().map(translator::fromDto).collect(Collectors.toSet());
    }

    @Override
    public Container getById(final String id) {
        return translator.fromDto(containerDAO.getById(id));
    }
}
