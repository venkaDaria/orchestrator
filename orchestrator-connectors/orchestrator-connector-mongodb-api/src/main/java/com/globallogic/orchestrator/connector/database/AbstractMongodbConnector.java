package com.globallogic.orchestrator.connector.database;

import com.globallogic.orchestrator.connector.exception.MongodbOperationException;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class AbstractMongodbConnector {

    @Qualifier("getMongoOperations")
    @Autowired
    private MongoOperations mongoOperations;

    protected void validate(final int len, final String name, final String... params) {
        if (params.length != len) {
            throw new MongodbOperationException("Can't insert " + name);
        }
    }

    protected void insert(final String collectionName, final DBObject object) {
        mongoOperations.insert(object, collectionName);
    }

    protected List<DBObject> getAll(final String collectionName) {
        return mongoOperations.getCollection(collectionName).find().toArray();
    }

    protected DBObject get(final String collectionName, final DBObject dbObject) {
        List<DBObject> results = mongoOperations.getCollection(collectionName).find(dbObject).toArray();
        if (results.size() != 1) {
            throw new MongodbOperationException("Too many results");
        }
        return results.get(0);
    }

    protected void remove(final String collectionName, final DBObject dbObject) {
        mongoOperations.remove(dbObject, collectionName);
    }
}
