package exception;

public class NodeException  extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

    public NodeException() {
        super();
    }

    public NodeException(String message) {
        super(message);
    }

    public NodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
