package com.globallogic.orchestrator.dao.config;

import com.globallogic.orchestrator.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOConfig {

    @Bean
    public DAOType getDAOType() {
        return DAOType.DB;
    }

    @Bean
    public NodeDAO getNodeDAO(DAOType type) {
        return DAOFactory.getInstance(type).getNodeDAO();
    }

    @Bean
    public ServiceDAO getServiceDAO(DAOType type) {
        return DAOFactory.getInstance(type).getServiceDAO();
    }

    @Bean
    public ContainerDAO getContainerDAO(DAOType type) {
        return DAOFactory.getInstance(type).getContainerDAO();
    }

}
