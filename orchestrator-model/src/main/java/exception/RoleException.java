package exception;

public class RoleException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public RoleException() {
		super();
	}

	public RoleException(String message) {
		super(message);
	}

	public RoleException(String message, Throwable cause) {
		super(message, cause);
	}
}
