package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOType;
import com.globallogic.orchestrator.dao.dto.NodeDTO;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.interfaces.NodeService;
import com.globallogic.orchestrator.service.translators.NodeTranslator;

import java.util.HashSet;
import java.util.Set;

public class NodeServiceImpl implements NodeService {

    private DAOType type;

    public NodeServiceImpl(final DAOType type) {
        this.type = type;
    }

    @Override
    public void save(final Set<Node> nodes) {
        Set<NodeDTO> set = new HashSet<>();

        NodeTranslator translator = new NodeTranslator();
        nodes.forEach(node -> set.add(translator.getDto(node)));

        DAOFactory.getInstance(type).getNodeDAO().save(set);
    }

    @Override
    public Set<Node> load() {
        Set<NodeDTO> set = DAOFactory.getInstance(type).getNodeDAO().load();
        Set<Node> nodes = new HashSet<>();

        NodeTranslator translator = new NodeTranslator();
        set.forEach(dto -> nodes.add(translator.fromDto(dto)));

        return nodes;
    }
}
