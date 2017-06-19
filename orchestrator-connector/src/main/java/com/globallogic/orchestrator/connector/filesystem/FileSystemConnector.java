package com.globallogic.orchestrator.connector.filesystem;

public interface FileSystemConnector {
	
	String read(String fileName);
	
	void write(String fileName, String text);
}
