package exception;

public class ImageReferenceException extends ValidationException {
	private static final int code = 100003;	
	
    public ImageReferenceException() {
        super(code, "ImageReference must be: \"server/name:tag@digestTag\"");
    }

    public ImageReferenceException(String message) {
        super(code, message);
    }

    public ImageReferenceException(String message, Throwable cause) {
        super(code, message, cause);
    }
    
    public ImageReferenceException(Throwable cause) {
        super(code, cause);
    }
}