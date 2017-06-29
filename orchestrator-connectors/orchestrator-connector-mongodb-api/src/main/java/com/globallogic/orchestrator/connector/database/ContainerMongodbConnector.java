package com.globallogic.orchestrator.connector.database;

import com.mongodb.DBObject;

import java.util.Set;
import java.util.function.Function;

public interface ContainerMongodbConnector {

    void insert(final String... param);

    <T> Set<T> getAll(final Function<DBObject, T> transform);

    <T> T getById(final Function<DBObject, T> transform, final String param);

    void remove(final String param);
}
