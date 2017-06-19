package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.filesystem.FileSystemDAOFactory;

public interface DAOFactory {

    ContainerDAO getContainerDAO();
    ServiceDAO getServiceDAO();
    NodeDAO getNodeDAO();

    static DAOFactory getDAOFactory(){
        return new FileSystemDAOFactory();
    }
}
