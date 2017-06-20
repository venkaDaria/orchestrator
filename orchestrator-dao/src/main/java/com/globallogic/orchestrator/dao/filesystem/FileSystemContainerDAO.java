package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.dao.exception.ContainerConfigurationException;
import com.globallogic.orchestrator.model.entity.Container;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileSystemContainerDAO implements ContainerDAO {
    private static final String SEPARATOR = ";";
    private static final String FILE_NAME = "containers.csv";

    private ContainerDTO getDTO(String line) {
        ContainerDTO container = new ContainerDTO();

        if (StringUtils.isBlank(line)) {
            throw new ContainerConfigurationException();
        }

        String[] values = line.split(SEPARATOR);

        if (values.length != 4) {
            throw new ContainerConfigurationException();
        }

        container.setId(values[0]);
        container.setNodeName(values[1]);
        container.setServiceName(values[2]);
        container.setStatus(values[3]);

        return container;
    }

    private String getString(Container container) {
        return container.getId() + SEPARATOR + container.getNode().getName() + SEPARATOR + container.getService().getName()
                + SEPARATOR + container.getStatus() + System.lineSeparator();
    }

    @Override
    public void save(Set<Container> containers) {
        StringBuilder sb = new StringBuilder();
        containers.forEach(container -> sb.append(getString(container)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<ContainerDTO> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        Set<ContainerDTO> containers = new HashSet<>();
        Arrays.stream(lines).forEach(line -> containers.add(getDTO(line)));
        return containers;
    }
}
