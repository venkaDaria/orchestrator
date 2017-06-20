package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;

public class DatabaseDAOFactoryImpl implements DAOFactory {
    private DatabaseDAOFactoryImpl() { }

    private static class DatabaseDAOFactoryHolder {
        private static final DatabaseDAOFactoryImpl INSTANCE = new DatabaseDAOFactoryImpl();
    }

    public synchronized static DatabaseDAOFactoryImpl getInstance() {
        return DatabaseDAOFactoryHolder.INSTANCE;
    }

    @Override
    public ContainerDAO getContainerDAO() {
        return new DatabaseContainerDAOImpl();
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new DatabaseServiceDAOImpl();
    }

    @Override
    public NodeDAO getNodeDAO() {
        return new DatabaseNodeDAOImpl();
    }
}
