package com.globallogic.orchestrator.mongodb;

import org.bson.Document;

import java.util.Set;
import java.util.function.Function;

public interface ServiceMongodbConnector {

    void insert(final String name, final String image, final Set<String> roles,
                final Set<String> ports, final Set<String> volumes);

    <T> Set<T> getAll(final Function<Document, T> transform);

    <T> T getByName(final Function<Document, T> transform, final String param);

    void remove(final String param);
}
