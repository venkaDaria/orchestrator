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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContainerServiceImpl implements ContainerService {

    private static final Logger LOG = LoggerFactory.getLogger(ContainerServiceImpl.class);

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
        LOG.debug("Save containers -> " + containers);
        containerDAO.save(containers.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<Container> load() {
        LOG.debug("Load containers");
        return containerDAO.load().stream().map(this::transform).collect(Collectors.toSet());
    }

    private Container transform(final ContainerDto containerDto) {
        LOG.debug("Transform ContainerDto " + containerDto);

        Service service = null;
        Node node = null;

        if (containerDto.getNodeName() != null) {
            node = translatorNode.fromDto(nodeDAO.getByName(containerDto.getNodeName()));
        }

        if (containerDto.getServiceName() != null) {
            service = translatorService.fromDto(serviceDAO.getByName(containerDto.getServiceName()));
        }

        return translator.fromDto(containerDto, node, service);
    }

    @Override
    public Container getById(final String id) {
        LOG.debug("Get container by id -> " + id);
        return transform(containerDAO.getById(id));
    }

    @Override
    public void remove(final String id) {
        LOG.debug("Remove container by id -> " + id);
        containerDAO.remove(id);
    }

    @Override
    public void add(final String id, final String status, final String node, final String server) {
        LOG.debug("Add container");
        containerDAO.add(id, status, node, server);
    }
}
