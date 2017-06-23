package com.globallogic.orchestrator.connector.database;

import java.util.Set;

public interface ContainerDatabaseConnector {

    void insert(final String... params);

    Set<String[]> getAll();

    String[] getById(String id);
}
