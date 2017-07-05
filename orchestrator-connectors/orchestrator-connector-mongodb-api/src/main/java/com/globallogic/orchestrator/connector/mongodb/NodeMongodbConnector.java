package com.globallogic.orchestrator.connector.mongodb;

import org.bson.Document;

import java.util.Set;
import java.util.function.Function;

public interface NodeMongodbConnector {

    void insert(final String name, final Set<String> roles);

    <T> Set<T> getAll(final Function<Document, T> transform);

    <T> T getByName(final Function<Document, T> transform, final String param);

    void remove(final String param);
}
