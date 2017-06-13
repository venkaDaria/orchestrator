package exception;

public class ImageReferenceValidationException extends ValidationException {
	private static final int CODE = 100003;	
	private static final String MESSAGE = "ImageReference must be: \"server/name:tag@digestTag\"";
	
    public ImageReferenceValidationException() {
        super(CODE, MESSAGE);
    }

    public ImageReferenceValidationException(String message) {
        super(CODE, message);
    }

    public ImageReferenceValidationException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
    
    public ImageReferenceValidationException(Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}