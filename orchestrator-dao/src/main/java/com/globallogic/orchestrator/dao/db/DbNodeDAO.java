package com.globallogic.orchestrator.dao.db;

import com.globallogic.orchestrator.connector.db.NodeDbConnector;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dto.NodeDTO;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DbNodeDAO implements NodeDAO {
    private static final String SEP = ";";

    @Override
    public void save(Set<Node> nodes) {
        Set<NodeDTO> set = new HashSet<>();
        nodes.forEach(node -> set.add(getDTO(node)));

        new NodeDbConnector().insert(set);
    }

    private static NodeDTO getDTO(Node node) {
        NodeDTO dto = new NodeDTO();
        dto.setName(node.getName());

        StringBuilder sb = new StringBuilder();
        node.getRoles().forEach(role -> sb.append(role.getValue()).append(SEP));
        dto.setRoles(sb.toString());

        return dto;
    }

    @Override
    public Set<Node> load() {
        Set<NodeDTO> set = new NodeDbConnector().getAll();

        Set<Node> nodes = new HashSet<>();
        set.forEach(dto -> nodes.add(getNode(dto)));
        return nodes;
    }

    private Node getNode(NodeDTO dto) {
        Node node = new Node();
        node.setName(dto.getName());

        Set<Role> roles = new HashSet<>();
        if (StringUtils.isNotEmpty(dto.getRoles())) {
            Arrays.stream(dto.getRoles().split(SEP)).forEach(role -> roles.add(new Role(role)));
        }
        node.setRoles(roles);

        return node;
    }
}
