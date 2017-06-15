package connector;

public interface FileSystemConnector {
	
	String read(String fileName);
	
	void write(String fileName, String text);
}
