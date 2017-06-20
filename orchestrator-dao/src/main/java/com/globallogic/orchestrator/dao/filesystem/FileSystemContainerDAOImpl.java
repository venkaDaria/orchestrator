package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.dto.ContainerDTO;
import com.globallogic.orchestrator.dao.exception.ContainerConfigurationException;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileSystemContainerDAOImpl implements ContainerDAO {
    private final String SEPARATOR;
    private static final String FILE_NAME = "containers.csv";

    public FileSystemContainerDAOImpl(final LocaleSeparator separator) {
        SEPARATOR = separator.toString();
    }

    private ContainerDTO getDTO(final String line) {
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

    private String getString(final ContainerDTO container) {
        if (container == null) {
            throw new ContainerConfigurationException();
        }
        return container.getId() + SEPARATOR + container.getNodeName() + SEPARATOR + container.getServiceName()
                + SEPARATOR + container.getStatus() + System.lineSeparator();
    }

    @Override
    public void save(final Set<ContainerDTO> containers) {
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
