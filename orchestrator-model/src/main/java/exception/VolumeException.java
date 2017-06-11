package exception;

public class VolumeException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public VolumeException() {
		super();
	}

	public VolumeException(String message) {
		super(message);
	}

	public VolumeException(String message, Throwable cause) {
		super(message, cause);
	}
}
