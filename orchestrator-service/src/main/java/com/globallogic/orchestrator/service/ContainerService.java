package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.connector.FileSystemConnectorImpl;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.service.exception.ContainerServiceException;
import org.apache.commons.lang.StringUtils;

public class ContainerService {
    private static final String FILE_NAME = "container.csv";

    private static final String SEP = ";";

    public void write(Container container) {
        write(FILE_NAME, container);
    }

    public void write(String fileName, Container container) {
        new FileSystemConnectorImpl().write(fileName, getStringCsv(container));
    }

    public Container read() {
        return read(FILE_NAME);
    }

    public Container read(String fileName) {
        return parse(new FileSystemConnectorImpl().read(fileName));
    }

    public static Container parse(String line) {
        Container container = new Container();

        if (StringUtils.isBlank(line)) {
            throw new ContainerServiceException();
        }

        String[] values = line.split(SEP);

        if (values.length != 4) {
            throw new ContainerServiceException();
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

    public static String getStringCsv(Container container) {
        return container.getId() + SEP + container.getNode().getName() + SEP + container.getService().getName()
                + SEP + container.getStatus() + System.lineSeparator();
    }
}
