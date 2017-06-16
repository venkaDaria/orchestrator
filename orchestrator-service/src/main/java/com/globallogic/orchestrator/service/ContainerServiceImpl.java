package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.connector.FileSystemConnectorImpl;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.exception.ContainerConfigurationException;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainerServiceImpl implements ContainerService {
    private static final String SEP = ";";
    private static final String FILE_NAME = "containers.csv";

    @Override
    public void save(final Set<Container> containers) {
        StringBuilder sb = new StringBuilder();
        containers.forEach(container -> sb.append(getStringCsv(container)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<Container> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        Set<Container> containers = new HashSet<>();
        Arrays.stream(lines).forEach(line -> containers.add(parse(line)));
        return containers;
    }

    private Container parse(String line) {
        Container container = new Container();

        if (StringUtils.isBlank(line)) {
            throw new ContainerConfigurationException();
        }

        String[] values = line.split(SEP);

        if (values.length != 4) {
            throw new ContainerConfigurationException();
        }

        container.setId(values[0]);

        Node node = new Node();
        node.setName(values[1]);
        container.setNode(node);

        Service service = new Service();
        service.setName(values[2]);
        container.setService(service);
        container.setStatus(Status.valueOf(values[3]));

        return container;
    }

    private static String getStringCsv(Container container) {
        return container.getId() + SEP + container.getNode().getName() + SEP + container.getService().getName()
                + SEP + container.getStatus() + System.lineSeparator();
    }
}
