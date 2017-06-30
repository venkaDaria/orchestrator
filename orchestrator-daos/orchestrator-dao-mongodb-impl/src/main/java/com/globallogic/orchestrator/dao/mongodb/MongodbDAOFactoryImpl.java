package com.globallogic.orchestrator.dao.mongodb;

import com.globallogic.orchestrator.dao.ContainerDAO;
import com.globallogic.orchestrator.dao.DAOFactory;
import com.globallogic.orchestrator.dao.NodeDAO;
import com.globallogic.orchestrator.dao.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongodbDAOFactoryImpl implements DAOFactory {

    @Autowired
    private MongodbContainerDAO mongodbContainerDAO;

    @Autowired
    private MongodbNodeDAO mongodbNodeDAO;

    @Autowired
    private MongodbServiceDAO mongodbServiceDAO;

    @Override
    public ContainerDAO getContainerDAO() {
        return mongodbContainerDAO;
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return mongodbServiceDAO;
    }

    @Override
    public NodeDAO getNodeDAO() {
        return mongodbNodeDAO;
    }
}
