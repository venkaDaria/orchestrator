package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.translators.ContainerDtoTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

    @Autowired
    private NodeService nodeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ContainerService containerService;

    @Autowired
    private ContainerDAO containerDAO;

    @Autowired
    private ContainerDtoTranslator translator;

    @Override
    public void save(final Configuration config) {
        LOG.debug("Save configuration -> " + config);
        Set<Container> containers = new HashSet<>();
        config.getNodes().forEach(node -> containers.addAll(node.getContainers()));

        nodeService.save(config.getNodes());
        serviceService.save(config.getServices());
        containerService.save(containers);
    }

    @Override
    public Configuration load() {
        Configuration config = new Configuration();

        Set<Node> nodes = nodeService.load();
        Set<Service> services = serviceService.load();
        Set<ContainerDto> containers = containerDAO.load();

        for (ContainerDto dto : containers) {
            Node node = nodes.stream().filter(n -> dto.getNodeName().equals(n.getName())).findAny().orElse(null);
            Service service = services.stream().filter(s -> dto.getServiceName()
                    .equals(s.getName())).findAny().orElse(null);

            translator.fromDto(dto, node, service);
        }

        config.setNodes(nodes);
        config.setServices(services);

        LOG.debug("Load configuration -> " + config);
        return config;
    }
}
