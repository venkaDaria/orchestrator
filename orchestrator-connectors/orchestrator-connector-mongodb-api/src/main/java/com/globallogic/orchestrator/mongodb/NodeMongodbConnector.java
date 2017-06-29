package com.globallogic.orchestrator.mongodb;

import com.mongodb.DBObject;

import java.util.Set;
import java.util.function.Function;

public interface NodeMongodbConnector {

    void insert(final String name, final Set<String> roles);

    <T> Set<T> getAll(final Function<DBObject, T> transform);

    <T> T getByName(final Function<DBObject, T> transform, final String param);

    void remove(final String param);
}
