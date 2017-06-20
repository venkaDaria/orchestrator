package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;

public class DatabaseDAOFactory implements DAOFactory {
    private DatabaseDAOFactory() { }

    private static class DatabaseDAOFactoryHolder {
        private static final DatabaseDAOFactory INSTANCE = new DatabaseDAOFactory();
    }

    public synchronized static DatabaseDAOFactory getInstance() {
        return DatabaseDAOFactoryHolder.INSTANCE;
    }

    @Override
    public ContainerDAO getContainerDAO() {
        return new DatabaseContainerDAO();
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new DatabaseServiceDAO();
    }

    @Override
    public NodeDAO getNodeDAO() {
        return new DatabaseNodeDAO();
    }
}
