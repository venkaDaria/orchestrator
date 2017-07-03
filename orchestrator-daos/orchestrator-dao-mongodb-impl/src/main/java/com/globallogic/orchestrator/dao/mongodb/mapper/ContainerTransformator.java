package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.ContainerDto;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContainerTransformator {

    private static final Logger LOG = LoggerFactory.getLogger(ContainerTransformator.class);

    public ContainerDto toDto(final Document dbObject) {
        LOG.debug("Get ContainerDto from dbObject -> " + dbObject);
        ContainerDto container = new ContainerDto();

        container.setId(dbObject.get("id").toString());
        container.setStatus(dbObject.get("status").toString());
        container.setNodeName(dbObject.get("node").toString());
        container.setServiceName(dbObject.get("service").toString());

        LOG.debug("Return ContainerDto -> " + container);
        return container;
    }
}
