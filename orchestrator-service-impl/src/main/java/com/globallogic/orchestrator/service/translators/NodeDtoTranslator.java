package com.globallogic.orchestrator.service.translators;

import com.globallogic.orchestrator.base.Translator;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import com.globallogic.orchestrator.model.StringValueObject;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.valueobject.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class NodeDtoTranslator implements Translator<Node, NodeDto> {

    private static final Logger LOG = LoggerFactory.getLogger(NodeDtoTranslator.class);
    
    @Override
    public NodeDto getDto(final Node model) {
        LOG.debug("Get NodeDto from -> " + model);
        NodeDto dto = new NodeDto();

        dto.setName(model.getName());
        dto.setRoles(model.getRoles().stream().map(StringValueObject::asString).collect(Collectors.toSet()));

        LOG.debug("Return NodeDto -> " + dto);
        return dto;
    }

    @Override
    public Node fromDto(final NodeDto dto) {
        LOG.debug("Get Node from -> " + dto);
        Node node = new Node();

        node.setName(dto.getName());
        node.setRoles(dto.getRoles().stream().map(Role::new).collect(Collectors.toSet()));

        LOG.debug("Return Node -> " + node);
        return node;
    }
}
