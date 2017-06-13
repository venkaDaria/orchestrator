package exception;

public class ProtocolValidationException extends ValidationException {
	private static final int CODE = 100006;	
	private static final String MESSAGE = "Value can't be null or empty";
	
    public ProtocolValidationException() {
        super(CODE, MESSAGE);
    }

    public ProtocolValidationException(String message) {
        super(CODE, message);
    }

    public ProtocolValidationException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
    
    public ProtocolValidationException(Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}