package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import com.globallogic.orchestrator.model.StringValueObject;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class NodeDtoTranslator implements Translator<Node, NodeDto> {

    @Override
    public NodeDto getDto(final Node model) {
        NodeDto dto = new NodeDto();

        dto.setName(model.getName());
        dto.setRoles(model.getRoles().stream().map(StringValueObject::asString).collect(Collectors.toSet()));

        return dto;
    }

    @Override
    public Node fromDto(final NodeDto dto) {
        Node node = new Node();

        node.setName(dto.getName());
        node.setRoles(dto.getRoles().stream().map(Role::new).collect(Collectors.toSet()));

        return node;
    }
}
