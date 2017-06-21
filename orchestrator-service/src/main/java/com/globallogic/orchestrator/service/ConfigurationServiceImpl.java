package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOSystem;
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

    private DAOSystem system;

    public ConfigurationServiceImpl(final DAOSystem system) {
        this.system = system;
    }

    @Override
    public void save(final Configuration config) {
        Set<Container> containers = new HashSet<>();
        config.getNodes().forEach(node -> containers.addAll(node.getContainers()));

        new NodeServiceImpl(system).save(config.getNodes());

        new ServiceServiceImpl(system).save(config.getServices());

        new ContainerServiceImpl(system).save(containers);
    }

    @Override
    public Configuration load() {
        Configuration config = new Configuration();

        Set<Node> nodes = new NodeServiceImpl(system).load();

        Set<Service> services = new ServiceServiceImpl(system).load();

        Set<ContainerDTO> containers = new ContainerServiceImpl(system).load();

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
