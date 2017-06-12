package exception;

public class ServiceException extends ValidationException {
	private static final int code = 100001;	
	
    public ServiceException() {
        super(code, "Can't do some operation with service");
    }

    public ServiceException(String message) {
        super(code, message);
    }

    public ServiceException(String message, Throwable cause) {
        super(code, message, cause);
    }
    
    public ServiceException(Throwable cause) {
        super(code, cause);
    }
    
    public int getCode() {
    	return code;
    }
}