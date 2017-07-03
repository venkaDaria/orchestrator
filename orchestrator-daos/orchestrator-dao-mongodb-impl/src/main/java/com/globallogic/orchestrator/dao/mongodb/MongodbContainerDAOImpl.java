package com.globallogic.orchestrator.dao.mongodb;

import com.globallogic.orchestrator.connector.mongodb.ContainerMongodbConnector;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.dao.mongodb.mapper.ContainerTransformator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class MongodbContainerDAOImpl implements MongodbContainerDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MongodbContainerDAOImpl.class);

    @Autowired
    private ContainerMongodbConnector connector;

    @Autowired
    private ContainerTransformator containerTransformator;

    @Override
    public void save(final Set<ContainerDto> containers) {
        LOG.debug("Save containers -> " + containers);
        containers.forEach(el -> connector.insert(el.getId(), el.getStatus(), el.getNodeName(), el.getServiceName()));
    }

    @Override
    public Set<ContainerDto> load() {
        LOG.debug("Load containers");
        return connector.getAll(containerTransformator::toDto);
    }

    @Override
    public ContainerDto getById(final String id) {
        LOG.debug("Get a container by id -> " + id);
        return connector.getById(containerTransformator::toDto, id);
    }

    @Override
    public void remove(final String id) {
        LOG.debug("Remove a container by id -> " + id);
        connector.remove(id);
    }

    @Override
    public void add(final String id, final String status, final String node, final String server) {
        LOG.debug("Insert a container");
        connector.insert(id, status, node, server);
    }
}
