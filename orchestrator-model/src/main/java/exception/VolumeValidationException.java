package exception;

public class VolumeValidationException extends ValidationException {
	private static final int CODE = 100005;	
	private static final String MESSAGE = "Volume must be: \"string:string\"";
	
    public VolumeValidationException() {
        super(CODE, MESSAGE);
    }

    public VolumeValidationException(String message) {
        super(CODE, message);
    }

    public VolumeValidationException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
    
    public VolumeValidationException(Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}