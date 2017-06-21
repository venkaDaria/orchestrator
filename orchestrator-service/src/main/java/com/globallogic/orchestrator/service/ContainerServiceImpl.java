package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.service.interfaces.ContainerService;
import com.globallogic.orchestrator.service.translators.ContainerDtoTranslator;

import java.util.Set;
import java.util.stream.Collectors;

public class ContainerServiceImpl implements ContainerService {

    private DAOType type;
    private ContainerDtoTranslator translator;

    public ContainerServiceImpl(final DAOType type) {
        this.type = type;
        translator = new ContainerDtoTranslator();
    }

    @Override
    public void save(final Set<Container> containers) {
        DAOFactory.getInstance(type).getContainerDAO().save(containers.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<ContainerDto> load() {
        return DAOFactory.getInstance(type).getContainerDAO().load();
    }
}
