package exception;

public abstract class ValidationException extends RuntimeException {	
    public ValidationException(int code, String message) {
        super(message);
    }

    public ValidationException(int code, String message, Throwable cause) {
        super(message, cause);
    }
    
    public ValidationException(int code, Throwable cause) {
        super(cause);
    }
    
    public abstract int getCode();
}
