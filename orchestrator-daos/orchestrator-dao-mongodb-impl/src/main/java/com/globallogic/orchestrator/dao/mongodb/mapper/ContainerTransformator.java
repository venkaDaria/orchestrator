package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;

@Component
public class ContainerTransformator {

    public ContainerDto toDto(final DBObject dbObject) {
        ContainerDto container = new ContainerDto();

        container.setId(dbObject.get("id").toString());
        container.setId(dbObject.get("status").toString());
        container.setId(dbObject.get("node").toString());
        container.setId(dbObject.get("service").toString());

        return container;
    }
}
