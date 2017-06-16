package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;

import java.util.HashSet;
import java.util.Set;

public class ConfigurationServiceImpl implements ConfigurationService {
    private static final String FILE_NAME_CONTAINERS = "containers.csv";
    private static final String FILE_NAME_NODES = "nodes.csv";
    private static final String FILE_NAME_SERVICES = "services.csv";

    @Override
    public void write(Configuration config) {
        Set<Container> containers = new HashSet<>();
        config.getNodes().forEach(node -> containers.addAll(node.getContainers()));

        new NodeServiceImpl().write(FILE_NAME_NODES, config.getNodes());

        new ServiceServiceImpl().write(FILE_NAME_SERVICES, config.getServices());

        new ContainerServiceImpl().write(FILE_NAME_CONTAINERS, containers);
    }

    @Override
    public Configuration read() {
        Configuration config = new Configuration();

        Set<Node> nodes = new NodeServiceImpl().read(FILE_NAME_NODES);

        Set<Service> services = new ServiceServiceImpl().read(FILE_NAME_SERVICES);

        Set<Container> containers = new ContainerServiceImpl().read(FILE_NAME_CONTAINERS);

        for (Container container : containers) {
            Node node = nodes.stream().filter(container.getNode()::equals).findAny().orElse(container.getNode());
            container.setNode(node);

            Service service = services.stream().filter(container.getService()::equals).findAny().orElse(container.getService());
            container.setService(service);
        }

        config.setNodes(nodes);
        config.setServices(services);

        return config;
    }
}
