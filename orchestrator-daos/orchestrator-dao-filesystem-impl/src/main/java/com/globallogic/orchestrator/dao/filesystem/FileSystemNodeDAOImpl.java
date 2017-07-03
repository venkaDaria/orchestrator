package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnector;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileSystemNodeDAOImpl implements FileSystemNodeDAO {

    private static final Logger LOG = LoggerFactory.getLogger(FileSystemNodeDAOImpl.class);

    private final String SEPARATOR;

    private static final String FILE_NAME = "nodes.csv";

    public FileSystemNodeDAOImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Autowired
    private FileSystemConnector connector;

    @Override
    public void save(final Set<NodeDto> nodes) {
        LOG.debug("Save nodes -> " + nodes);

        StringBuilder sb = new StringBuilder();
        nodes.forEach(node -> sb.append(getString(node)));

        connector.write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<NodeDto> load() {
        LOG.debug("Load nodes");
        String[] lines = connector.read(FILE_NAME).split(System.lineSeparator());

        return Arrays.stream(lines).map(this::getDTO).collect(Collectors.toSet());
    }

    @Override
    public NodeDto getByName(final String name) {
        LOG.debug("Get a node by name -> " + name);
        String[] lines = connector.read(FILE_NAME).split(System.lineSeparator());

        return getDTO(Arrays.stream(lines).filter(line -> line.split(SEPARATOR)[0].equals(name)).findAny().orElse(null));
    }

    @Override
    public void remove(final String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(final String name, final List<String> roles) {
        throw new UnsupportedOperationException();
    }

    private String getString(final NodeDto node) {
        LOG.debug("Get a String from NodeDto -> " + node);

        StringBuilder sb = new StringBuilder();
        node.getRoles().forEach(role -> sb.append(role).append(SeparatorHolder.getSeparatorString()));
        String rolesString = sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();

        return node.getName() + SEPARATOR + rolesString + System.lineSeparator();
    }

    private NodeDto getDTO(final String line) {
        LOG.debug("Get a NodeDto from String -> " + line);
        NodeDto node = new NodeDto();

        int idx = line.indexOf(SEPARATOR);

        node.setName(line.substring(0, idx));

        String roles = line.substring(idx + 1);
        if (StringUtils.isNotEmpty(roles)) {
            node.setRoles(new HashSet<>(Arrays.asList(roles.split(SEPARATOR))));
        } else {
            node.setRoles(new HashSet<>());
        }

        LOG.debug("Return a NodeDto -> " + node);
        return node;
    }
}
