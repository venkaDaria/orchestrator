package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;

public class FileSystemDAOFactoryImpl implements DAOFactory {
    private LocaleSeparator separator = LocaleSeparator.SEMICOLON;

    private FileSystemDAOFactoryImpl() { }

    private static class FileSystemDAOFactoryHolder {
        private static final FileSystemDAOFactoryImpl INSTANCE = new FileSystemDAOFactoryImpl();
    }

    public synchronized static FileSystemDAOFactoryImpl getInstance() {
        return FileSystemDAOFactoryHolder.INSTANCE;
    }

    public LocaleSeparator getSeparator() {
        return separator;
    }

    public void setSeparator(LocaleSeparator separator) {
        this.separator = separator;
    }

    @Override
    public ContainerDAO getContainerDAO() {
        return new FileSystemContainerDAOImpl(separator);
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new FileSystemServiceDAOImpl(separator);
    }

    @Override
    public NodeDAO getNodeDAO() {
        return new FileSystemNodeDAOImpl(separator);
    }
}
