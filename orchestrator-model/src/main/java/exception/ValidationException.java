package exception;

/**
 * Exception's codes interval is 100001-100007
 */
public abstract class ValidationException extends RuntimeException {	
	private final int code;	
	
	public ValidationException(final int code, final String message) {        
		super(message);
		this.code = code;
    }

    public ValidationException(final int code, final String message, final Throwable cause) {
        super(message, cause);
		this.code = code;
    } 
    
    public int getCode() {
    	return code;
    }
}