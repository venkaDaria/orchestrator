package exception;

public class ImageReferenceException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

    public ImageReferenceException() {
        super();
    }

    public ImageReferenceException(String message) {
        super(message);
    }

    public ImageReferenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
