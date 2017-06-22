package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;

public class FileSystemDAOFactoryImpl implements DAOFactory {

    private FileSystemDAOFactoryImpl() { }

    private static class FileSystemDAOFactoryHolder {
        private static final FileSystemDAOFactoryImpl INSTANCE = new FileSystemDAOFactoryImpl();
    }

    public synchronized static FileSystemDAOFactoryImpl getInstance() {
        return FileSystemDAOFactoryHolder.INSTANCE;
    }

    @Override
    public ContainerDAO getContainerDAO() {
        return new FileSystemContainerDAOImpl();
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new FileSystemServiceDAOImpl();
    }

    @Override
    public NodeDAO getNodeDAO() {
        return new FileSystemNodeDAOImpl();
    }
}
