package com.globallogic.orchestrator.dao.mongodb;

import com.globallogic.orchestrator.connector.mongodb.NodeMongodbConnector;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import com.globallogic.orchestrator.dao.mongodb.mapper.NodeTransformator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MongodbNodeDAOImpl implements MongodbNodeDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MongodbNodeDAOImpl.class);

    @Autowired
    private NodeMongodbConnector connector;

    @Autowired
    private NodeTransformator nodeTransformator;

    @Override
    public void save(final Set<NodeDto> nodes) {
        LOG.debug("Save nodes -> " + nodes);
        nodes.forEach(el -> connector.insert(el.getName(), el.getRoles()));
    }

    @Override
    public Set<NodeDto> load() {
        LOG.debug("Load nodes");
        return connector.getAll(nodeTransformator::toDto);
    }

    @Override
    public NodeDto getByName(final String name) {
        LOG.debug("Get a node by name -> " + name);
        return connector.getByName(nodeTransformator::toDto, name);
    }

    @Override
    public void remove(final String name) {
        LOG.debug("Remove a node by name -> " + name);
        connector.remove(name);
    }

    @Override
    public void add(final String name, final List<String> roles) {
        LOG.debug("Insert a node");
        connector.insert(name, new HashSet<>(roles));
    }
}
