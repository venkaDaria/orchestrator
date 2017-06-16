package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.connector.FileSystemConnectorImpl;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class ConfigurationService {
    private static final String FILE_NAME = "config.csv";

    public void write(Configuration config) {
        StringBuilder sb = new StringBuilder();
        Set<Container> containers = new HashSet<>();

        for (Node node: config.getNodes()) {
            containers.addAll(node.getContainers());
            sb.append(NodeService.getStringCsv(node));
        }

        sb.append(System.lineSeparator());

        for (Service service : config.getServices()) {
            sb.append(ServiceService.getStringCsv(service));
        }

        sb.append(System.lineSeparator());

        for (Container container : containers) {
            sb.append(ContainerService.getStringCsv(container));
        }

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    public Configuration read() {
        Configuration config = new Configuration();

        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        int i = 0;

        Set<Node> nodes = new HashSet<>();
        for (; i < lines.length && StringUtils.isNotEmpty(lines[i]); i++) {
            nodes.add(NodeService.parse(lines[i]));
        }

        i++;

        Set<Service> services = new HashSet<>();
        for (; i < lines.length && StringUtils.isNotEmpty(lines[i]); i++) {
            services.add(ServiceService.parse(lines[i]));
        }

        i++;

        for (; i < lines.length; i++) {
            Container container = ContainerService.parse(lines[i]);

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
