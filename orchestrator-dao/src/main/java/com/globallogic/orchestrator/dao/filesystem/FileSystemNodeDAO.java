package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.connector.filesystem.FileSystemConnectorImpl;
import com.globallogic.orchestrator.dao.exception.NodeConfigurationException;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileSystemNodeDAO implements NodeDAO {
    private final String SEPARATOR;
    private static final String FILE_NAME = "nodes.csv";

    public FileSystemNodeDAO(LocaleSeparator separator) {
        SEPARATOR = separator.toString();
    }

    private String getString(Node node) {
        StringBuilder lineBuilder = new StringBuilder(node.getName());
        for (Role r : node.getRoles()) {
            lineBuilder.append(SEPARATOR).append(r.getValue());
        }
        return lineBuilder.append(System.lineSeparator()).toString();
    }

    private Node parse(String line) {
        Node node = new Node();

        if (StringUtils.isBlank(line)) {
            throw new NodeConfigurationException();
        }

        String[] values = line.split(SEPARATOR);

        node.setName(values[0]);

        Set<Role> roles = new HashSet<>();
        for (int i = 1; i < values.length; i++) {
            roles.add(new Role(values[i]));
        }

        node.setRoles(roles);
        return node;
    }

    @Override
    public void save(final Set<Node> nodes) {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(node -> sb.append(getString(node)));

        new FileSystemConnectorImpl().write(FILE_NAME, sb.toString());
    }

    @Override
    public Set<Node> load() {
        String[] lines = new FileSystemConnectorImpl().read(FILE_NAME).split(System.lineSeparator());

        Set<Node> nodes = new HashSet<>();
        Arrays.stream(lines).forEach(line -> nodes.add(parse(line)));
        return nodes;
    }
}
