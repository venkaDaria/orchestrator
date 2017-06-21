package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import com.globallogic.orchestrator.model.StringValueObject;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;

import java.util.HashSet;
import java.util.stream.Collectors;

public class NodeDtoTranslator implements Translator<Node, NodeDto> {

    @Override
    public NodeDto getDto(Node model) {
        NodeDto dto = new NodeDto();
        dto.setName(model.getName());
        dto.setRoles(model.getRoles().stream().map(StringValueObject::asString).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public Node fromDto(NodeDto dto) {
        Node node = new Node();
        node.setName(dto.getName());

        if (!dto.getRoles().isEmpty()) {
            node.setRoles(dto.getRoles().stream().map(Role::new).collect(Collectors.toSet()));
        } else {
            node.setRoles(new HashSet<>());
        }

        return node;
    }
}
