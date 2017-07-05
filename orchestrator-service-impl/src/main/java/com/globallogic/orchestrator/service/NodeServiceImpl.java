package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.translators.NodeDtoTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NodeServiceImpl implements NodeService {

    private static final Logger LOG = LoggerFactory.getLogger(NodeServiceImpl.class);

    @Autowired
    private NodeDAO nodeDAO;

    @Autowired
    private NodeDtoTranslator translator;

    @Override
    public void save(final Set<Node> nodes) {
        LOG.debug("Save nodes -> " + nodes);
        nodeDAO.save(nodes.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<Node> load() {
        LOG.debug("Load nodes");
        return nodeDAO.load().stream().map(translator::fromDto).collect(Collectors.toSet());
    }

    @Override
    public Node getByName(final String name) {
        LOG.debug("Get node by name -> " + name);
        return translator.fromDto(nodeDAO.getByName(name));
    }

    @Override
    public void remove(final String name) {
        LOG.debug("Remove node by name -> " + name);
        nodeDAO.remove(name);
    }

    @Override
    public void add(final String name, final List<String> roles) {
        LOG.debug("Add node");
        nodeDAO.add(name, roles);
    }
}