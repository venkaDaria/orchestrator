package com.globallogic.orchestrator.dao.mongodb;

import com.globallogic.orchestrator.mongodb.NodeMongodbConnector;
import com.globallogic.orchestrator.dao.dto.NodeDto;
import com.globallogic.orchestrator.dao.mongodb.mapper.NodeTransformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public class MongodbNodeDAOImpl implements MongodbNodeDAO {

    @Autowired
    private NodeMongodbConnector connector;

    @Autowired
    private NodeTransformator nodeTransformator;

    @Override
    @Transactional
    public void save(final Set<NodeDto> nodes) {
        nodes.forEach(el -> connector.insert(el.getName(), el.getRoles()));
    }

    @Override
    public Set<NodeDto> load() {
        return connector.getAll(nodeTransformator::toDto);
    }

    @Override
    public NodeDto getByName(final String name) {
        return connector.getByName(nodeTransformator::toDto, name);
    }

    @Override
    public void remove(final String name) {
        connector.remove(name);
    }
}
