package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.interfaces.NodeService;
import com.globallogic.orchestrator.service.translators.NodeDtoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeDAO nodeDAO;

    @Autowired
    private NodeDtoTranslator translator;

    @Override
    public void save(final Set<Node> nodes) {
        nodeDAO.save(nodes.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<Node> load() {
        return nodeDAO.load().stream().map(translator::fromDto).collect(Collectors.toSet());
    }

    @Override
    public Node getByName(String name) {
        return null;
        //TODO:
    }
}