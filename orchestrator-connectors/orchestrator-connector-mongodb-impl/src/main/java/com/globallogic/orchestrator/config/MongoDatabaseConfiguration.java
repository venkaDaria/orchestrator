package com.globallogic.orchestrator.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MongoDatabaseConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public MongoDatabase getMongoDatabase() {
        return new MongoClient(env.getProperty("orchestrator.connector.mongodb.host"),
                Integer.parseInt(env.getProperty("orchestrator.connector.mongodb.port")))
                 .getDatabase(env.getProperty("orchestrator.connector.mongodb.databaseName"));
    }
}
