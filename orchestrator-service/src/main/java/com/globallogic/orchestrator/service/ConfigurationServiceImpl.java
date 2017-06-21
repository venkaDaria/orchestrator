package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.interfaces.ConfigurationService;

import java.util.HashSet;
import java.util.Set;

public class ConfigurationServiceImpl implements ConfigurationService {

    private DAOType type;

    public ConfigurationServiceImpl(final DAOType type) {
        this.type = type;
    }

    @Override
    public void save(final Configuration config) {
        Set<Container> containers = new HashSet<>();
        config.getNodes().forEach(node -> containers.addAll(node.getContainers()));

        new NodeServiceImpl(type).save(config.getNodes());

        new ServiceServiceImpl(type).save(config.getServices());

        new ContainerServiceImpl(type).save(containers);
    }

    @Override
    public Configuration load() {
        Configuration config = new Configuration();

        Set<Node> nodes = new NodeServiceImpl(type).load();

        Set<Service> services = new ServiceServiceImpl(type).load();

        Set<ContainerDTO> containers = new ContainerServiceImpl(type).load();

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
