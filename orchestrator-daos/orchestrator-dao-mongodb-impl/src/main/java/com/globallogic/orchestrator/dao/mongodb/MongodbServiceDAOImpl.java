package com.globallogic.orchestrator.dao.mongodb;

import com.globallogic.orchestrator.connector.mongodb.ServiceMongodbConnector;
import com.globallogic.orchestrator.dao.dto.ServiceDto;
import com.globallogic.orchestrator.dao.mongodb.mapper.ServiceTransformator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MongodbServiceDAOImpl implements MongodbServiceDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MongodbServiceDAOImpl.class);

    @Autowired
    private ServiceMongodbConnector connector;

    @Autowired
    private ServiceTransformator serviceTransformator;

    @Override
    public void save(final Set<ServiceDto> services) {
        LOG.debug("Save services -> " + services);
        services.forEach(el -> connector.insert(el.getName(), el.getImage(),el.getRoles(), el.getPorts(), el.getVolumes()));
    }

    @Override
    public Set<ServiceDto> load() {
        LOG.debug("Load services");
        return connector.getAll(serviceTransformator::toDto);
    }

    @Override
    public ServiceDto getByName(final String name) {
        LOG.debug("Get a service by name -> " + name);
        return connector.getByName(serviceTransformator::toDto, name);
    }

    @Override
    public void remove(final String name) {
        LOG.debug("Remove a service by name -> " + name);
        connector.remove(name);
    }

    @Override
    public void add(final String name, final String image, final List<String> roles, final List<String> ports, final List<String> volumes) {
        LOG.debug("Insert a service");
        connector.insert(name, image, new HashSet<>(roles), new HashSet<>(ports), new HashSet<>(volumes));
    }
}
