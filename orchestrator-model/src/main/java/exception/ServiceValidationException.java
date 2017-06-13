package exception;

public class ServiceValidationException extends ValidationException {
	private static final int CODE = 100001;	
	private static final String MESSAGE = "Can't do some operation with service";
	
    public ServiceValidationException() {
        super(CODE, MESSAGE);
    }

    public ServiceValidationException(String message) {
        super(CODE, message);
    }

    public ServiceValidationException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
    
    public ServiceValidationException(Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}