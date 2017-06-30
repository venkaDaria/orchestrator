package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class ContainerTransformator {

    public ContainerDto toDto(final Document dbObject) {
        ContainerDto container = new ContainerDto();

        container.setId(dbObject.get("id").toString());
        container.setStatus(dbObject.get("status").toString());
        container.setNodeName(dbObject.get("node").toString());
        container.setServiceName(dbObject.get("service").toString());

        return container;
    }
}
