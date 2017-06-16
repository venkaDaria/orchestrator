package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.connector.FileSystemConnectorImpl;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.exception.NodeServiceException;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class NodeService {
    private static final String FILE_NAME = "node.csv";

    private static final String SEP = ";";

    public void write(Node node) {
        write(FILE_NAME, node);
    }

    public void write(String fileName, Node node) {
        new FileSystemConnectorImpl().write(fileName, getStringCsv(node));
    }

    public Node read() {
        return read(FILE_NAME);
    }

    public Node read(String fileName) {
        return parse(new FileSystemConnectorImpl().read(fileName));
    }

    public static Node parse(String line) {
        Node node = new Node();

        if (StringUtils.isBlank(line)) {
            throw new NodeServiceException();
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

    public static String getStringCsv(Node node) {
        StringBuilder lineBuilder = new StringBuilder(node.getName());
        for (Role r : node.getRoles()) {
            lineBuilder.append(SEP).append(r.getValue());
        }
        return lineBuilder.append(System.lineSeparator()).toString();
    }
}
