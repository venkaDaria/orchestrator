package com.globallogic.orchestrator.dao.db;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;

public class DbDAOFactory implements DAOFactory {
    private static DbDAOFactory instance;

    public synchronized static DbDAOFactory getInstance() {
        if (instance == null) {
            instance = new DbDAOFactory();
        }
        return instance;
    }

    private DbDAOFactory() { }

    @Override
    public ContainerDAO getContainerDAO() {
        return new DbContainerDAO();
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new DbServiceDAO();
    }

    @Override
    public NodeDAO getNodeDAO() {
        return new DbNodeDAO();
    }
}
