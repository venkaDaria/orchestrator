package exception;

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
    
    public ValidationException(int code, Throwable cause) {
        super(cause);
		this.code = code;
    }   
    
    public int getCode() {
    	return code;
    }
}
