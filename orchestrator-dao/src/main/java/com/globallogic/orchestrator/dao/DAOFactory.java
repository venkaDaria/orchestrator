package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.database.DatabaseDAOFactoryImpl;
import com.globallogic.orchestrator.dao.exception.SystemNotSupportException;
import com.globallogic.orchestrator.dao.filesystem.FileSystemDAOFactoryImpl;
import com.globallogic.orchestrator.dao.filesystem.LocaleSeparator;

public interface DAOFactory {
    ContainerDAO getContainerDAO();
    ServiceDAO getServiceDAO();
    NodeDAO getNodeDAO();

    static DAOFactory getInstance(DAOSystem system){
        switch(system) {
            case FILE_SYSTEM:
                return FileSystemDAOFactoryImpl.getInstance();
            case DB:
                return DatabaseDAOFactoryImpl.getInstance();
            default:
                throw new SystemNotSupportException();
        }
    }

    static LocaleSeparator getSeparator() {
        return FileSystemDAOFactoryImpl.getInstance().getSeparator();
    }

    static void setSeparator(LocaleSeparator separator) {
        FileSystemDAOFactoryImpl.getInstance().setSeparator(separator);
    }
}
