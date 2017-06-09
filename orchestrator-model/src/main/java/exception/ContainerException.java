package exception;

public class ContainerException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ContainerException() {
		super();
	}
	
	public ContainerException(final String message) {
		super(message);
	}
	
	public ContainerException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
