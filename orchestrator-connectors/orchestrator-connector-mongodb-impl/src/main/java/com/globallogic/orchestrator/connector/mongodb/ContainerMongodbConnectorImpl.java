package com.globallogic.orchestrator.connector.mongodb;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class ContainerMongodbConnectorImpl extends AbstractMongodbConnector implements ContainerMongodbConnector {

    private static final Logger LOG = LoggerFactory.getLogger(ContainerMongodbConnectorImpl.class);

    private static String COLLECTION_NAME = "containers";

    @Override
    public void insert(final String... params) {
        validate(4, "container", params);

        Document dbObject = new Document();
        dbObject.put("id", params[0]);
        dbObject.put("status", params[1]);
        dbObject.put("node", params[2]);
        dbObject.put("service", params[3]);

        insert(COLLECTION_NAME, dbObject);
        LOG.debug("Insert a container -> " + dbObject);
    }

    @Override
    public <T> Set<T> getAll(final Function<Document, T> transform) {
        LOG.debug("Get containers");
        return getAll(COLLECTION_NAME).stream().map(transform).collect(Collectors.toSet());
    }

    @Override
    public <T> T getById(final Function<Document, T> transform, final String param) {
        Document dbObject = new Document();
        dbObject.put("id", param);

        LOG.debug("Get a container by id -> " + param);
        return transform.apply(get(COLLECTION_NAME, dbObject));
    }

    @Override
    public void remove(final String param) {
        Document dbObject = new Document();
        dbObject.put("id", param);

        remove(COLLECTION_NAME, dbObject);
        LOG.debug("Remove a container by id -> " + param);
    }
}
