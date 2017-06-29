package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnector;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileSystemContainerDAOImpl implements FileSystemContainerDAO {

    private final String SEPARATOR;

    private static final String FILE_NAME = "containers.csv";

    public FileSystemContainerDAOImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Autowired
    private FileSystemConnector connector;

    @Override
    public void save(final Set<ContainerDto> containers) {
        StringBuilder sb = new StringBuilder();
        containers.forEach(container -> sb.append(getString(container)));

        connector.write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<ContainerDto> load() {
        String[] lines = connector.read(FILE_NAME).split(System.lineSeparator());

        return Arrays.stream(lines).map(this::getDTO).collect(Collectors.toSet());
    }

    @Override
    public ContainerDto getById(final String id) {
        String[] lines = connector.read(FILE_NAME).split(System.lineSeparator());

        return getDTO(Arrays.stream(lines).filter(line -> line.split(SEPARATOR)[0].equals(id)).findAny().orElse(null));
    }

    @Override
    public void remove(final String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final String id, final String status, final String node, final String server) {
        throw new UnsupportedOperationException();
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
