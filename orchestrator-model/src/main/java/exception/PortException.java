package exception;

public class PortException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

    public PortException() {
        super();
    }

    public PortException(String message) {
        super(message);
    }

    public PortException(String message, Throwable cause) {
        super(message, cause);
    }
}