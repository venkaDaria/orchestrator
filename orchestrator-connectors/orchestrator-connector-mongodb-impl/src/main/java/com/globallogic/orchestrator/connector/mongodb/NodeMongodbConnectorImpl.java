package com.globallogic.orchestrator.connector.mongodb;

import com.globallogic.orchestrator.mongodb.AbstractMongodbConnector;
import com.globallogic.orchestrator.mongodb.NodeMongodbConnector;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class NodeMongodbConnectorImpl extends AbstractMongodbConnector implements NodeMongodbConnector {

    private static String COLLECTION_NAME = "nodes";

    public void insert(final String name, final Set<String> roles) {
        Document dbObject = new Document();
        dbObject.put("name", name);
        dbObject.put("roles", roles);

        insert(COLLECTION_NAME, dbObject);
    }

    @Override
    public <T> Set<T> getAll(final Function<Document, T> transform) {
        return getAll(COLLECTION_NAME).stream().map(transform).collect(Collectors.toSet());
    }

    @Override
    public <T> T getByName(final Function<Document, T> transform, final String param) {
        Document dbObject = new Document();
        dbObject.put("name", param);

        return transform.apply(get(COLLECTION_NAME, dbObject));
    }

    @Override
    public void remove(final String param) {
        Document dbObject = new Document();
        dbObject.put("name", param);

        remove(COLLECTION_NAME, dbObject);
    }
}
