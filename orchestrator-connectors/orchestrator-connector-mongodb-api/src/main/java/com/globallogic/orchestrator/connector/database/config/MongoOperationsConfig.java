package com.globallogic.orchestrator.connector.database.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoOperationsConfig {

    @Bean
    public MongoOperations getMongoOperations() {
        return new MongoTemplate(new MongoClient("127.0.0.1", 27017), "config");
    }
}
