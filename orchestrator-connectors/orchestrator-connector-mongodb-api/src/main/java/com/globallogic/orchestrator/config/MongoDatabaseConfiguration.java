package com.globallogic.orchestrator.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MongoDatabaseConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(MongoDatabaseConfiguration.class);

    @Autowired
    private Environment env;

    @Bean
    public MongoDatabase getMongoDatabase() {
        LOG.debug("Get MongoDatabase");
        return new MongoClient(env.getProperty("mongodb.host"), Integer.parseInt(env.getProperty("mongodb.port")))
                 .getDatabase(env.getProperty("mongodb.databaseName"));
    }
}
