package com.globallogic.orchestrator.connector.mongodb;

import com.globallogic.orchestrator.connector.exception.MongodbOperationException;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class AbstractMongodbConnector {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractMongodbConnector.class);
    @Autowired
    private MongoDatabase mongoDatabase;

    protected void validate(final int len, final String name, final String... params) {
        if (params.length != len) {
            throw new MongodbOperationException("Can't insert " + name);
        }
        LOG.debug("Validation is successful");
    }

    protected void insert(final String collectionName, final Document dc) {
        mongoDatabase.getCollection(collectionName).insertOne(dc);
        LOG.debug("Insert an object");
    }

    protected List<Document> getAll(final String collectionName) {
        LOG.debug("Get all objects");
        return mongoDatabase.getCollection(collectionName).find().into(new ArrayList<>());
    }

    protected Document get(final String collectionName, final Document dc) {
        LOG.debug("Get an object");
        return mongoDatabase.getCollection(collectionName).find(dc).into(new ArrayList<>()).get(0);
    }

    protected void remove(final String collectionName, final Document dc) {
        mongoDatabase.getCollection(collectionName).deleteOne(dc);
        LOG.debug("Remove an object");
    }
}
