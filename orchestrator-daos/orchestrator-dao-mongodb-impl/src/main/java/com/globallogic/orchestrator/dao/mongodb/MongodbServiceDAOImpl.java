package com.globallogic.orchestrator.dao.mongodb;

import com.globallogic.orchestrator.mongodb.ServiceMongodbConnector;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import com.globallogic.orchestrator.dao.mongodb.mapper.ServiceTransformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class MongodbServiceDAOImpl implements MongodbServiceDAO {

    @Autowired
    private ServiceMongodbConnector connector;

    @Autowired
    private ServiceTransformator serviceTransformator;

    @Override
    @Transactional
    public void save(final Set<ServiceDto> services) {
        services.forEach(el -> connector.insert(el.getName(), el.getImage(),el.getRoles(), el.getPorts(), el.getVolumes()));
    }

    @Override
    public Set<ServiceDto> load() {
        return connector.getAll(serviceTransformator::toDto);
    }

    @Override
    public ServiceDto getByName(final String name) {
        return connector.getByName(serviceTransformator::toDto, name);
    }

    @Override
    public void remove(final String name) {
        connector.remove(name);
    }
}
