package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.model.entity.Node;
import com.globallogic.orchestrator.service.interfaces.NodeService;

import java.util.Set;

public class NodeServiceImpl implements NodeService {
    @Override
    public void save(final Set<Node> nodes) {
        DAOFactory.getDAOFactory().getNodeDAO().save(nodes);
    }

    @Override
    public Set<Node> load() {
        return DAOFactory.getDAOFactory().getNodeDAO().load();
    }
}
