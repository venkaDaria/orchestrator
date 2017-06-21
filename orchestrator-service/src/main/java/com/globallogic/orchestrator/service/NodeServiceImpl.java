package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDTO;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import com.globallogic.orchestrator.service.interfaces.NodeService;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NodeServiceImpl implements NodeService {
    private DAOType type;
    private final String SEPARATOR;

    public NodeServiceImpl(final DAOType type) {
        this.type = type;
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Override
    public void save(final Set<Node> nodes) {
        Set<NodeDTO> set = new HashSet<>();
        nodes.forEach(node -> set.add(getDTO(node)));

        DAOFactory.getInstance(type).getNodeDAO().save(set);
    }

    @Override
    public Set<Node> load() {
        Set<NodeDTO> set = DAOFactory.getInstance(type).getNodeDAO().load();

        Set<Node> nodes = new HashSet<>();
        set.forEach(dto -> nodes.add(getNode(dto)));

        return nodes;
    }

    private NodeDTO getDTO(final Node node) {
        NodeDTO dto = new NodeDTO();
        dto.setName(node.getName());

        StringBuilder sb = new StringBuilder();
        node.getRoles().forEach(role -> sb.append(role.getValue()).append(SEPARATOR));

        String str = sb.toString();
        str = (StringUtils.isNotEmpty(str)) ? str.substring(0, str.length() - 1) : str;
        dto.setRoles(str);

        return dto;
    }

    private Node getNode(final NodeDTO dto) {
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
