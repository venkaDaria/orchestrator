package exception;

public class VolumeValidationException extends ValidationException {
	private static final int CODE = 100005;	
	private static final String MESSAGE = "Volume must be: \"string:string\"";
	
    public VolumeValidationException() {
        super(CODE, MESSAGE);
    }

    public VolumeValidationException(final String message) {
        super(CODE, message);
    }

    public VolumeValidationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }
    
    public VolumeValidationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}