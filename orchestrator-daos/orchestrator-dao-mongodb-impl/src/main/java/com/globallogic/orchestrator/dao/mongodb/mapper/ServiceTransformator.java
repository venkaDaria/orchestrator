package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ServiceTransformator {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceTransformator.class);

    public ServiceDto toDto(final Document dbObject) {
        LOG.debug("Get ServiceDto from dbObject -> " + dbObject);
        ServiceDto serviceDto = new ServiceDto();

        serviceDto.setName(dbObject.get("name").toString());
        serviceDto.setImage(dbObject.get("image").toString());

        serviceDto.setRoles(((ArrayList<String>)dbObject.get("roles")).stream().map(Object::toString).collect(Collectors.toSet()));
        serviceDto.setPorts(((ArrayList<String>)dbObject.get("ports")).stream().map(Object::toString).collect(Collectors.toSet()));
        serviceDto.setVolumes(((ArrayList<String>)dbObject.get("volumes")).stream().map(Object::toString).collect(Collectors.toSet()));

        LOG.debug("Return ServiceDto -> " + serviceDto);
        return serviceDto;
    }
}
