package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.translators.ContainerDtoTranslator;
import com.globallogic.orchestrator.service.translators.NodeDtoTranslator;
import com.globallogic.orchestrator.service.translators.ServiceDtoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private ContainerDAO containerDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private NodeDAO nodeDAO;

    @Autowired
    private ContainerDtoTranslator translator;

    @Autowired
    private ServiceDtoTranslator translatorService;

    @Autowired
    private NodeDtoTranslator translatorNode;

    @Override
    public void save(final Set<Container> containers) {
        containerDAO.save(containers.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<Container> load() {
        return containerDAO.load().stream().map(this::transform).collect(Collectors.toSet());
    }

    private Container transform(final ContainerDto containerDto) {
        Node node = translatorNode.fromDto(nodeDAO.getByName(containerDto.getNodeName()));
        Service service = translatorService.fromDto(serviceDAO.getByName(containerDto.getServiceName()));
        return translator.fromDto(containerDto, node, service);
    }

    @Override
    public Container getById(final String id) {
        return transform(containerDAO.getById(id));
    }
}
