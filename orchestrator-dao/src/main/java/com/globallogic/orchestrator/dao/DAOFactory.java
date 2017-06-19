package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.db.DbDAOFactory;
import com.globallogic.orchestrator.dao.filesystem.FileSystemDAOFactory;

public interface DAOFactory {

    int FILE_SYSTEM = 1;
    int DB = 2;

    ContainerDAO getContainerDAO();
    ServiceDAO getServiceDAO();
    NodeDAO getNodeDAO();

    static DAOFactory getDAOFactory(int num){
        switch(num) {
            case FILE_SYSTEM:
                return new FileSystemDAOFactory();
            case DB:
                return new DbDAOFactory();
            default:
                return null;
        }
    }
}
