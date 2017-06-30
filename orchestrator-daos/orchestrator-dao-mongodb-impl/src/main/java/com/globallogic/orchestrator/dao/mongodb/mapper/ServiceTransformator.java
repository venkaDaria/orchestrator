package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.ServiceDto;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ServiceTransformator {

    public ServiceDto toDto(final Document dbObject) {
        ServiceDto serviceDto = new ServiceDto();

        serviceDto.setName(dbObject.get("name").toString());
        serviceDto.setImage(dbObject.get("image").toString());

        serviceDto.setRoles(((ArrayList<String>)dbObject.get("roles")).stream().map(Object::toString).collect(Collectors.toSet()));
        serviceDto.setPorts(((ArrayList<String>)dbObject.get("ports")).stream().map(Object::toString).collect(Collectors.toSet()));
        serviceDto.setVolumes(((ArrayList<String>)dbObject.get("volumes")).stream().map(Object::toString).collect(Collectors.toSet()));

        return serviceDto;
    }
}
