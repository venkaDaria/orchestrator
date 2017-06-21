package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.SeparatorHolder;
import com.globallogic.orchestrator.dao.dto.NodeDTO;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NodeTranslatorImpl implements Translator<Node, NodeDTO> {
    private final String SEPARATOR;

    public NodeTranslatorImpl() {
        SEPARATOR = SeparatorHolder.getSeparatorString();
    }

    @Override
    public NodeDTO getDto(Node model) {
        NodeDTO dto = new NodeDTO();
        dto.setName(model.getName());

        StringBuilder sb = new StringBuilder();
        model.getRoles().forEach(role -> sb.append(role.getValue()).append(SEPARATOR));

        String str = sb.toString();
        str = (StringUtils.isNotEmpty(str)) ? str.substring(0, str.length() - 1) : str;
        dto.setRoles(str);

        return dto;
    }

    @Override
    public Node fromDto(NodeDTO dto) {
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
