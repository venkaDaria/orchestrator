package exception;

public class PortValidationException extends ValidationException {
	private static final int CODE = 100004;	
	private static final String MESSAGE = "Port must be \"int:int/protocol\" or \"int/protocol\"";
	
    public PortValidationException() {
        super(CODE, MESSAGE);
    }

    public PortValidationException(String message) {
        super(CODE, message);
    }

    public PortValidationException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
    
    public PortValidationException(Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}