package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.interfaces.NodeService;
import com.globallogic.orchestrator.service.translators.NodeDtoTranslator;

import java.util.Set;
import java.util.stream.Collectors;

public class NodeServiceImpl implements NodeService {

    private DAOType type;
    private NodeDtoTranslator translator;

    public NodeServiceImpl(final DAOType type) {
        this.type = type;
        translator = new NodeDtoTranslator();
    }

    @Override
    public void save(final Set<Node> nodes) {
        DAOFactory.getInstance(type).getNodeDAO().save(nodes.stream().map(translator::getDto).collect(Collectors.toSet()));
    }

    @Override
    public Set<Node> load() {
        return DAOFactory.getInstance(type).getNodeDAO().load().stream().map(translator::fromDto).collect(Collectors.toSet());
    }
}
