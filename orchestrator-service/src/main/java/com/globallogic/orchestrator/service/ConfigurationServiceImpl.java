package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.interfaces.ConfigurationService;
import com.globallogic.orchestrator.service.interfaces.ContainerService;
import com.globallogic.orchestrator.service.interfaces.NodeService;
import com.globallogic.orchestrator.service.interfaces.ServiceService;
import com.globallogic.orchestrator.service.translators.ContainerDtoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ContainerService containerService;

    @Autowired
    private ContainerDtoTranslator translator;

    @Override
    public void save(final Configuration config) {
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
        Set<ContainerDto> containers = containerService.load();

        for (ContainerDto dto : containers) {
            Node node = nodes.stream().filter(n -> dto.getNodeName().equals(n.getName())).findAny().orElse(null);
            Service service = services.stream().filter(s -> dto.getServiceName()
                    .equals(s.getName())).findAny().orElse(null);

            translator.fromDto(dto, node, service);
        }

        config.setNodes(nodes);
        config.setServices(services);

        return config;
    }
}
