package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.database.DatabaseDAOFactoryImpl;
import com.globallogic.orchestrator.dao.exception.SystemNotSupportException;
import com.globallogic.orchestrator.dao.filesystem.FileSystemDAOFactoryImpl;

public interface DAOFactory {
    ContainerDAO getContainerDAO();

    ServiceDAO getServiceDAO();

    NodeDAO getNodeDAO();

    static DAOFactory getInstance(DAOType type) {
        switch (type) {
            case FILE_SYSTEM:
                return FileSystemDAOFactoryImpl.getInstance();
            case DB:
                return DatabaseDAOFactoryImpl.getInstance();
            default:
                throw new SystemNotSupportException();
        }
    }
}
