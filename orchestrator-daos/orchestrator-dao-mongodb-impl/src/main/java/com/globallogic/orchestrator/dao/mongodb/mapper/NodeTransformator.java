package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class NodeTransformator {

    public NodeDto toDto(final Document dbObject) {
        NodeDto nodeDto = new NodeDto();

        nodeDto.setName(dbObject.get("name").toString());
        nodeDto.setRoles(((ArrayList<String>)dbObject.get("roles")).stream().map(Object::toString).collect(Collectors.toSet()));

        return nodeDto;
    }
}
