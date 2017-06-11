package exception;

public class ProtocolException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

    public ProtocolException() {
        super();
    }

    public ProtocolException(String message) {
        super(message);
    }

    public ProtocolException(String message, Throwable cause) {
        super(message, cause);
    }
}
