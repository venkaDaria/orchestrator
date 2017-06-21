package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileSystemNodeDAOImpl implements NodeDAO {
    private final String SEPARATOR;
    private static final String FILE_NAME = "nodes.csv";

    public FileSystemNodeDAOImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Override
    public void save(final Set<NodeDTO> nodes) {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(node -> sb.append(getString(node)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<NodeDTO> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        Set<NodeDTO> nodes = new HashSet<>();
        Arrays.stream(lines).forEach(line -> nodes.add(getDTO(line)));
        return nodes;
    }

    private String getString(final NodeDTO node) {
        return node.getName() + SEPARATOR + node.getRoles() + System.lineSeparator();
    }

    private NodeDTO getDTO(final String line) {
        NodeDTO node = new NodeDTO();

        int idx = line.indexOf(SEPARATOR);

        node.setName(line.substring(0, idx));
        node.setRoles(line.substring(idx + 1));
        return node;
    }
}
