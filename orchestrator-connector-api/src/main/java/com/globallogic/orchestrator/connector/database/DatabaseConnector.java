package com.globallogic.orchestrator.connector.database;

import java.util.Set;

public interface DatabaseConnector {

    void insert(final String... params);

    Set<String[]> getAll();
}
