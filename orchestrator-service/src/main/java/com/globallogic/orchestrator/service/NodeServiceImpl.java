package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.interfaces.NodeService;

import java.util.Set;

public class NodeServiceImpl implements NodeService {
    @Override
    public void save(final Set<Node> nodes) {
        DAOFactory.getDAOFactory(DAOFactory.FILE_SYSTEM).getNodeDAO().save(nodes);
    }

    @Override
    public Set<Node> load() {
        return DAOFactory.getDAOFactory(DAOFactory.FILE_SYSTEM).getNodeDAO().load();
    }
}
