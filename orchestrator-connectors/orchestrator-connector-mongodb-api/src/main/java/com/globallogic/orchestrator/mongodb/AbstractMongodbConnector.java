package com.globallogic.orchestrator.mongodb;

import com.globallogic.orchestrator.connector.exception.MongodbOperationException;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
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
        return mongoOperations.getCollection(collectionName).find(dbObject).toArray().get(0);
    }

    protected void remove(final String collectionName, final Query searchQuery) {
        mongoOperations.remove(searchQuery, collectionName);
    }
}
