package com.globallogic.orchestrator.dao;

public interface DAOFactory {

    ContainerDAO getContainerDAO();

    ServiceDAO getServiceDAO();

    NodeDAO getNodeDAO();
}
