package exception;

public class NodeException extends ValidationException {
	private static final int code = 100002;	
	
    public NodeException() {
        super(code, "Can't do some operation with node");
    }

    public NodeException(String message) {
        super(code, message);
    }

    public NodeException(String message, Throwable cause) {
        super(code, message, cause);
    }
    
    public NodeException(Throwable cause) {
        super(code, cause);
    }
}