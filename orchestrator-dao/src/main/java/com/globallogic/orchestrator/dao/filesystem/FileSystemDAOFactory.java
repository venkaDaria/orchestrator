package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;

public class FileSystemDAOFactory implements DAOFactory {
    private static FileSystemDAOFactory instance;

    public synchronized static FileSystemDAOFactory getInstance() {
        if (instance == null) {
            instance = new FileSystemDAOFactory();
        }
        return instance;
    }

    private FileSystemDAOFactory() {}

    @Override
    public ContainerDAO getContainerDAO() {
        return new FileSystemContainerDAO();
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new FileSystemServiceDAO();
    }

    @Override
    public NodeDAO getNodeDAO() {
        return new FileSystemNodeDAO();
    }
}
