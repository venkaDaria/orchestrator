package com.globallogic.orchestrator.connector.database;

import com.mongodb.DBObject;

import java.util.Set;
import java.util.function.Function;

public interface ServiceMongodbConnector {

    void insert(final String name, final String image, final Set<String> roles,
                final Set<String> ports, final Set<String> volumes);

    <T> Set<T> getAll(final Function<DBObject, T> transform);

    <T> T getByName(final Function<DBObject, T> transform, final String param);

    void remove(final String param);
}
