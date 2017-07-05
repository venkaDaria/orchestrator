package com.globallogic.orchestrator.connector.mongodb;

import org.bson.Document;

import java.util.Set;
import java.util.function.Function;

public interface ContainerMongodbConnector {

    void insert(final String... param);

    <T> Set<T> getAll(final Function<Document, T> transform);

    <T> T getById(final Function<Document, T> transform, final String param);

    void remove(final String param);
}
