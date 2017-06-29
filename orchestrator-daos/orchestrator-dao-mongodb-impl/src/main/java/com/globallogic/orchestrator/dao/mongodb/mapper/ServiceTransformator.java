package com.globallogic.orchestrator.dao.mongodb.mapper;

import com.globallogic.orchestrator.dao.dto.ServiceDto;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import org.springframework.stereotype.Component;

@Component
public class ServiceTransformator {

    public ServiceDto toDto(final DBObject dbObject) {
        ServiceDto serviceDto = new ServiceDto();

        serviceDto.setName(dbObject.get("name").toString());
        serviceDto.setName(dbObject.get("image").toString());

        serviceDto.setRoles(((BasicDBList)dbObject.get("roles")).keySet());
        serviceDto.setPorts(((BasicDBList)dbObject.get("ports")).keySet());
        serviceDto.setVolumes(((BasicDBList)dbObject.get("volumes")).keySet());

        return serviceDto;
    }
}
