package com.globallogic.orchestrator.dao.connector;

public interface FileSystemConnector {
	
	String read(String fileName);
	
	void write(String fileName, String text);
}
