package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.NodeDto;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class NodeTransformator {

    private static final Logger LOG = LoggerFactory.getLogger(NodeTransformator.class);

    public NodeDto toDto(final Document dbObject) {
        LOG.debug("Get NodeDto from dbObject -> " + dbObject);
        NodeDto nodeDto = new NodeDto();

        nodeDto.setName(dbObject.get("name").toString());
        nodeDto.setRoles(((ArrayList<String>)dbObject.get("roles")).stream().map(Object::toString).collect(Collectors.toSet()));

        LOG.debug("Return NodeDto -> " + nodeDto);
        return nodeDto;
    }
}
