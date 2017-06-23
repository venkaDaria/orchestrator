package com.globallogic.orchestrator.connector.database;

import java.util.Set;

public interface NodeDatabaseConnector {

    void insert(final String... params);

    Set<String[]> getAll();

    String[] getByName(String name);
}
