package model;

public class ContainerException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ContainerException() {
		super();
	}
	
	public ContainerException(String message) {
		super(message);
	}
	
	public ContainerException(String message, Throwable cause) {
		super(message, cause);
	}
}
