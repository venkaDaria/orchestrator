package com.globallogic.orchestrator.connector.filesystem;

public interface FileSystemConnector {

    String read(final String fileName);

    void write(final String fileName, final String text);
}
