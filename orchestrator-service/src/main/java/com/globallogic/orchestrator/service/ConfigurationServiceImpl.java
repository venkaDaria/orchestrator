package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.service.interfaces.ConfigurationService;
import com.globallogic.orchestrator.dao.model.Status;
import com.globallogic.orchestrator.dao.model.entity.Configuration;
import com.globallogic.orchestrator.dao.model.entity.Container;
import com.globallogic.orchestrator.dao.model.entity.Node;
import com.globallogic.orchestrator.dao.model.entity.Service;

import java.util.HashSet;
import java.util.Set;

public class ConfigurationServiceImpl implements ConfigurationService {

    @Override
    public void save(Configuration config) {
        Set<Container> containers = new HashSet<>();
        config.getNodes().forEach(node -> containers.addAll(node.getContainers()));

        new NodeServiceImpl().save(config.getNodes());

        new ServiceServiceImpl().save(config.getServices());

        new ContainerServiceImpl().save(containers);
    }

    @Override
    public Configuration load() {
        Configuration config = new Configuration();

        Set<Node> nodes = new NodeServiceImpl().load();

        Set<Service> services = new ServiceServiceImpl().load();

        Set<ContainerDTO> containers = new ContainerServiceImpl().load();

        Container container;
        for (ContainerDTO dto : containers) {
            container = new Container();
            container.setId(dto.getId());
            container.setStatus(Status.valueOf(dto.getStatus()));

            Node node = nodes.stream().filter(n -> dto.getNodeName().equals(n.getName())).findAny().orElse(null);
            container.setNode(node);

            Service service = services.stream().filter(s -> dto.getServiceName().equals(s.getName())).findAny().orElse(null);
            container.setService(service);
        }

        config.setNodes(nodes);
        config.setServices(services);

        return config;
    }
}
