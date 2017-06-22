package com.globallogic.orchestrator.dao.filesystem;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileSystemDAOFactoryImpl implements DAOFactory {

    @Autowired
    private FileSystemContainerDAOImpl fileSystemContainerDAO;

    @Autowired
    private FileSystemNodeDAOImpl fileSystemNodeDAO;

    @Autowired
    private FileSystemServiceDAOImpl fileSystemServiceDAO;

    @Override
    public ContainerDAO getContainerDAO() {
        return fileSystemContainerDAO;
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return fileSystemServiceDAO;
    }

    @Override
    public NodeDAO getNodeDAO() {
        return fileSystemNodeDAO;
    }
}
