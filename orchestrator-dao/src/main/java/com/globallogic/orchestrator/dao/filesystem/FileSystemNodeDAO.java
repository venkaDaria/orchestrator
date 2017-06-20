package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.dto.NodeDTO;
import com.globallogic.orchestrator.dao.exception.NodeConfigurationException;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileSystemNodeDAO implements NodeDAO {
    private final String SEPARATOR;
    private static final String FILE_NAME = "nodes.csv";

    public FileSystemNodeDAO(final LocaleSeparator separator) {
        SEPARATOR = separator.toString();
    }

    private String getString(final NodeDTO node) {
        if (node == null) {
            throw new NodeConfigurationException();
        }
        return node.getName() + SEPARATOR + node.getRoles() + System.lineSeparator();
    }

    private NodeDTO parse(final String line) {
        NodeDTO node = new NodeDTO();

        if (StringUtils.isBlank(line)) {
            throw new NodeConfigurationException();
        }

        int idx = line.indexOf(SEPARATOR);

        node.setName(line.substring(0, idx));
        node.setRoles(line.substring(idx + 1));
        return node;
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
        Arrays.stream(lines).forEach(line -> nodes.add(parse(line)));
        return nodes;
    }
}
