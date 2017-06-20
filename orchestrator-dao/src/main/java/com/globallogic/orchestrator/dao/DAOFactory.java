package com.globallogic.orchestrator.dao;

import com.globallogic.orchestrator.dao.database.DatabaseDAOFactory;
import com.globallogic.orchestrator.dao.exception.SystemNotSupportException;
import com.globallogic.orchestrator.dao.filesystem.FileSystemDAOFactory;
import com.globallogic.orchestrator.dao.filesystem.LocaleSeparator;

public interface DAOFactory {
    ContainerDAO getContainerDAO();
    ServiceDAO getServiceDAO();
    NodeDAO getNodeDAO();

    static DAOFactory getInstance(DAOSystem system){
        switch(system) {
            case FILE_SYSTEM:
                return FileSystemDAOFactory.getInstance();
            case DB:
                return DatabaseDAOFactory.getInstance();
            default:
                throw new SystemNotSupportException();
        }
    }

    static LocaleSeparator getSeparator() {
        return FileSystemDAOFactory.getInstance().getSeparator();
    }

    static void setSeparator(LocaleSeparator separator) {
        FileSystemDAOFactory.getInstance().setSeparator(separator);
    }
}
