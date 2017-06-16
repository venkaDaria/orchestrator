package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;

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

        Set<Container> containers = new ContainerServiceImpl().load();

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
