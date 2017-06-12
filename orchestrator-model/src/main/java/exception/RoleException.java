package exception;

public class RoleException extends ValidationException {
	private static final int code = 100007;	
	
    public RoleException() {
        super(code, "Value can't be null or empty");
    }

    public RoleException(String message) {
        super(code, message);
    }

    public RoleException(String message, Throwable cause) {
        super(code, message, cause);
    }
    
    public RoleException(Throwable cause) {
        super(code, cause);
    }
}