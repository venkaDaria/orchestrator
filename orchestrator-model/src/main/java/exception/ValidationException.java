package exception;

/**
 * Exception's codes intervals is 100001-100007
 */
public abstract class ValidationException extends RuntimeException {	
	private final int code;	
	
	public ValidationException(int code, String message) {        
		super(message);
		this.code = code;
    }

    public ValidationException(int code, String message, Throwable cause) {
        super(message, cause);
		this.code = code;
    } 
    
    public int getCode() {
    	return code;
    }
}
