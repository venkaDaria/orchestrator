package com.globallogic.orchestrator.service;

import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.DAOSystem;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.service.interfaces.NodeService;

import java.util.Set;

public class NodeServiceImpl implements NodeService {
    private DAOSystem system;

    public NodeServiceImpl(final DAOSystem system) {
        this.system = system;
    }

    @Override
    public void save(final Set<Node> nodes) {
        DAOFactory.getInstance(system).getNodeDAO().save(nodes);
    }

    @Override
    public Set<Node> load() {
        return DAOFactory.getInstance(system).getNodeDAO().load();
    }
}
