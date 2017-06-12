package exception;

public class ProtocolException extends ValidationException {
	private static final int code = 100006;	
	
    public ProtocolException() {
        super(code, "Value can't be null or empty");
    }

    public ProtocolException(String message) {
        super(code, message);
    }

    public ProtocolException(String message, Throwable cause) {
        super(code, message, cause);
    }
    
    public ProtocolException(Throwable cause) {
        super(code, cause);
    }
}