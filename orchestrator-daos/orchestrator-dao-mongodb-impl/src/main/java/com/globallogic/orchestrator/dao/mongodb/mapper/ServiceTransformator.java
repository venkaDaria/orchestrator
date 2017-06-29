package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.ServiceDto;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ServiceTransformator {

    public ServiceDto toDto(final DBObject dbObject) {
        ServiceDto serviceDto = new ServiceDto();

        serviceDto.setName(dbObject.get("name").toString());
        serviceDto.setImage(dbObject.get("image").toString());

        serviceDto.setRoles(((BasicDBList)dbObject.get("roles")).stream().map(Object::toString).collect(Collectors.toSet()));
        serviceDto.setPorts(((BasicDBList)dbObject.get("ports")).stream().map(Object::toString).collect(Collectors.toSet()));
        serviceDto.setVolumes(((BasicDBList)dbObject.get("volumes")).stream().map(Object::toString).collect(Collectors.toSet()));

        return serviceDto;
    }
}
