package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;

public class FileSystemDAOFactory implements DAOFactory {
    private LocaleSeparator separator = LocaleSeparator.SEMICOLON;

    private static FileSystemDAOFactory instance;

    public synchronized static FileSystemDAOFactory getInstance() {
        if (instance == null) {
            instance = new FileSystemDAOFactory();
        }
        return instance;
    }

    private FileSystemDAOFactory() { }

    public LocaleSeparator getSeparator() {
        return separator;
    }

    public void setSeparator(LocaleSeparator separator) {
        this.separator = separator;
    }

    @Override
    public ContainerDAO getContainerDAO() {
        return new FileSystemContainerDAO(separator);
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new FileSystemServiceDAO(separator);
    }

    @Override
    public NodeDAO getNodeDAO() {
        return new FileSystemNodeDAO(separator);
    }
}
