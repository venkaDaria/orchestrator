package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FileSystemNodeDAOImpl implements NodeDAO {
    private final String SEPARATOR;
    private static final String FILE_NAME = "nodes.csv";

    public FileSystemNodeDAOImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Override
    public void save(final Set<NodeDto> nodes) {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(node -> sb.append(getString(node)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<NodeDto> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        return Arrays.stream(lines).map(this::getDTO).collect(Collectors.toSet());
    }

    private String getString(NodeDto node) {
        StringBuilder sb = new StringBuilder();
        node.getRoles().forEach(role -> sb.append(role).append(SeparatorHolder.getSeparatorString()));
        String rolesString = sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();

        return node.getName() + SEPARATOR + rolesString + System.lineSeparator();
    }

    private NodeDto getDTO(final String line) {
        NodeDto node = new NodeDto();

        int idx = line.indexOf(SEPARATOR);

        node.setName(line.substring(0, idx));

        String roles = line.substring(idx + 1);
        if (StringUtils.isNotEmpty(roles)) {
            node.setRoles(new HashSet<>(Arrays.asList(roles.split(SEPARATOR))));
        } else {
            node.setRoles(new HashSet<>());
        }
        return node;
    }
}
