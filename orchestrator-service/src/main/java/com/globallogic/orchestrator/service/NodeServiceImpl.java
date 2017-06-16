package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.connector.FileSystemConnectorImpl;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.exception.NodeConfigurationException;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NodeServiceImpl implements NodeService {
    private static final String SEP = ";";

    @Override
    public void write(final String fileName, final Set<Node> nodes) {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(node -> sb.append(getStringCsv(node)));

        new FileSystemConnectorImpl().write(fileName, sb.toString());
    }

    @Override
    public Set<Node> read(final String fileName) {
        String[] lines = new FileSystemConnectorImpl().read(fileName).split(System.lineSeparator());

        Set<Node> nodes = new HashSet<>();
        Arrays.stream(lines).forEach(line -> nodes.add(parse(line)));
        return nodes;
    }

    private Node parse(final String line) {
        Node node = new Node();

        if (StringUtils.isBlank(line)) {
            throw new NodeConfigurationException();
        }

        String[] values = line.split(SEP);

        node.setName(values[0]);

        Set<Role> roles = new HashSet<>();
        for (int i = 1; i < values.length; i++) {
            roles.add(new Role(values[i]));
        }

        node.setRoles(roles);
        return node;
    }

    private String getStringCsv(final Node node) {
        StringBuilder lineBuilder = new StringBuilder(node.getName());
        for (Role r : node.getRoles()) {
            lineBuilder.append(SEP).append(r.getValue());
        }
        return lineBuilder.append(System.lineSeparator()).toString();
    }
}
