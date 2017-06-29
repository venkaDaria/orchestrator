package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.NodeDto;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;

@Component
public class NodeTransformator {

    public NodeDto toDto(final DBObject dbObject) {
        NodeDto nodeDto = new NodeDto();

        nodeDto.setName(dbObject.get("name").toString());
        nodeDto.setRoles(((BasicDBList)dbObject.get("roles")).keySet());

        return nodeDto;
    }
}
