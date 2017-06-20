package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.db.DbDAOFactory;
import com.globallogic.orchestrator.dao.exception.SystemNotSupportException;
import com.globallogic.orchestrator.dao.filesystem.FileSystemDAOFactory;

public interface DAOFactory {

    ContainerDAO getContainerDAO();
    ServiceDAO getServiceDAO();
    NodeDAO getNodeDAO();

    static DAOFactory getInstance(DAOSystem system){
        switch(system) {
            case FILE_SYSTEM:
                return FileSystemDAOFactory.getInstance();
            case DB:
                return DbDAOFactory.getInstance();
            default:
                throw new SystemNotSupportException();
        }
    }
}
