package com.globallogic.orchestrator.mongodb;

import com.globallogic.orchestrator.connector.exception.MongodbOperationException;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class AbstractMongodbConnector {

    @Autowired
    private MongoDatabase mongoDatabase;

    protected void validate(final int len, final String name, final String... params) {
        if (params.length != len) {
            throw new MongodbOperationException("Can't insert " + name);
        }
    }

    protected void insert(final String collectionName, final Document dc) {
        mongoDatabase.getCollection(collectionName).insertOne(dc);
    }

    protected List<Document> getAll(final String collectionName) {
        return mongoDatabase.getCollection(collectionName).find().into(new ArrayList<>());
    }

    protected Document get(final String collectionName, final Document dc) {
        return mongoDatabase.getCollection(collectionName).find(dc).into(new ArrayList<>()).get(0);
    }

    protected void remove(final String collectionName, final Document dc) {
        mongoDatabase.getCollection(collectionName).deleteOne(dc);
    }
}
