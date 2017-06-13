package exception;

public class NodeValidationException extends ValidationException {
	private static final int CODE = 100002;	
	private static final String MESSAGE = "Can't do some operation with node";
	
    public NodeValidationException() {
        super(CODE, MESSAGE);
    }

    public NodeValidationException(String message) {
        super(CODE, message);
    }

    public NodeValidationException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
    
    public NodeValidationException(Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}