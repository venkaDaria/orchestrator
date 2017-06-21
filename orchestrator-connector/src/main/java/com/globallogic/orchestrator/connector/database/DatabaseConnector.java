package com.globallogic.orchestrator.connector.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public interface DatabaseConnector {

    void insert(final Connection con, final String... params);

    Set<String[]> getAll(final Connection con) throws SQLException;

    String[] extract(final ResultSet rs) throws SQLException;

}
