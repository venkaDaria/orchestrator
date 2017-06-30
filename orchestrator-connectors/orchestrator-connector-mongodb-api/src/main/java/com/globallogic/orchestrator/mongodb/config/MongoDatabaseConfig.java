package com.globallogic.orchestrator.mongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDatabaseConfig {

    @Bean
    public MongoDatabase getMongoDatabase() {
         return new MongoClient("127.0.0.1", 27017).getDatabase("config");
    }
}
