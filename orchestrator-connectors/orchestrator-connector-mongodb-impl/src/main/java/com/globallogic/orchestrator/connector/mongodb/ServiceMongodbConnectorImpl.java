package com.globallogic.orchestrator.connector.mongodb;

import com.globallogic.orchestrator.mongodb.AbstractMongodbConnector;
import com.globallogic.orchestrator.mongodb.ServiceMongodbConnector;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ServiceMongodbConnectorImpl extends AbstractMongodbConnector implements ServiceMongodbConnector {

    private static String COLLECTION_NAME = "services";

    public void insert(final String name, final String image, final Set<String> roles,
                       final Set<String> ports, final Set<String> volumes) {
        Document dbObject = new Document();

        dbObject.put("name", name);
        dbObject.put("image", image);
        dbObject.put("roles", roles);
        dbObject.put("ports", ports);
        dbObject.put("volumes", volumes);

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
