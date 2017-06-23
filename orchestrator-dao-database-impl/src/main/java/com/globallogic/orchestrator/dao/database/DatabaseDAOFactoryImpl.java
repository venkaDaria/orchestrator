package com.globallogic.orchestrator.dao.database;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;
import com.globallogic.orchestrator.dao.filesystem.FileSystemContainerDAOImpl;
import com.globallogic.orchestrator.dao.filesystem.FileSystemNodeDAOImpl;
import com.globallogic.orchestrator.dao.filesystem.FileSystemServiceDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseDAOFactoryImpl implements DAOFactory {

    @Autowired
    private DatabaseContainerDAO fileSystemContainerDAO;

    @Autowired
    private DatabaseNodeDAO fileSystemNodeDAO;

    @Autowired
    private DatabaseServiceDAO fileSystemServiceDAO;

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
