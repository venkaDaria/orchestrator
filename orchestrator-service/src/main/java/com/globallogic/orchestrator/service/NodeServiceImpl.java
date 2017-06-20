package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOSystem;
import com.globallogic.orchestrator.dao.dto.NodeDTO;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.interfaces.NodeService;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NodeServiceImpl implements NodeService {
    private DAOSystem system;
    private static final String SEPARATOR = DAOFactory.getSeparator().toString();

    public NodeServiceImpl(final DAOSystem system) {
        this.system = system;
    }

    @Override
    public void save(final Set<Node> nodes) {
        Set<NodeDTO> set = new HashSet<>();
        nodes.forEach(node -> set.add(getDTO(node)));

        DAOFactory.getInstance(system).getNodeDAO().save(set);
    }

    @Override
    public Set<Node> load() {
        Set<NodeDTO> set = DAOFactory.getInstance(system).getNodeDAO().load();

        Set<Node> nodes = new HashSet<>();
        set.forEach(dto -> nodes.add(getNode(dto)));

        return nodes;
    }

    private static NodeDTO getDTO(Node node) {
        NodeDTO dto = new NodeDTO();
        dto.setName(node.getName());

        StringBuilder sb = new StringBuilder();
        node.getRoles().forEach(role -> sb.append(role.getValue()).append(SEPARATOR));
        dto.setRoles(sb.toString());

        return dto;
    }

    private Node getNode(NodeDTO dto) {
        Node node = new Node();
        node.setName(dto.getName());

        Set<Role> roles = new HashSet<>();
        if (StringUtils.isNotEmpty(dto.getRoles())) {
            Arrays.stream(dto.getRoles().split(SEPARATOR)).forEach(role -> roles.add(new Role(role)));
        }
        node.setRoles(roles);

        return node;
    }
}
