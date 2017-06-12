package exception;

public class PortException extends ValidationException {
	private static final int code = 100004;	
	
    public PortException() {
        super(code, "Port must be \"int:int/protocol\" or \"int/protocol\"");
    }

    public PortException(String message) {
        super(code, message);
    }

    public PortException(String message, Throwable cause) {
        super(code, message, cause);
    }
    
    public PortException(Throwable cause) {
        super(code, cause);
    }
}