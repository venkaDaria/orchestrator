package com.globallogic.orchestrator.dao.mongodb;

import com.globallogic.orchestrator.mongodb.ContainerMongodbConnector;
import com.globallogic.orchestrator.dao.dto.ContainerDto;
import com.globallogic.orchestrator.dao.mongodb.mapper.ContainerTransformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class MongodbContainerDAOImpl implements MongodbContainerDAO {

    @Autowired
    private ContainerMongodbConnector connector;

    @Autowired
    private ContainerTransformator containerTransformator;

    @Override
    @Transactional
    public void save(final Set<ContainerDto> containers) {
        containers.forEach(el -> connector.insert(el.getId(), el.getStatus(), el.getNodeName(), el.getServiceName()));
    }

    @Override
    public Set<ContainerDto> load() {
        return connector.getAll(containerTransformator::toDto);
    }

    @Override
    public ContainerDto getById(final String id) {
        return connector.getById(containerTransformator::toDto, id);
    }

    @Override
    public void remove(final String id) {
        connector.remove(id);
    }

    @Override
    public void add(final String id, final String status, final String node, final String server) {
        connector.insert(id, status, node, server);
    }
}
