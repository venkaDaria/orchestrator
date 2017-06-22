package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ContainerDto;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FileSystemContainerDAOImpl implements ContainerDAO {

    private final String SEPARATOR;

    private static final String FILE_NAME = "containers.csv";

    public FileSystemContainerDAOImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Override
    public void save(final Set<ContainerDto> containers) {
        StringBuilder sb = new StringBuilder();
        containers.forEach(container -> sb.append(getString(container)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<ContainerDto> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        return Arrays.stream(lines).map(this::getDTO).collect(Collectors.toSet());
    }

    private String getString(final ContainerDto container) {
        return container.getId() + SEPARATOR + container.getNodeName() + SEPARATOR + container.getServiceName()
                + SEPARATOR + container.getStatus() + System.lineSeparator();
    }

    private ContainerDto getDTO(final String line) {
        ContainerDto container = new ContainerDto();

        String[] values = line.split(SEPARATOR);

        container.setId(values[0]);
        container.setNodeName(values[1]);
        container.setServiceName(values[2]);
        container.setStatus(values[3]);

        return container;
    }
}
