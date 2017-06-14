package exception;

public class ValueObjectValidationException extends ValidationException {
	private static final int CODE = 100008;
	private static final String MESSAGE = "Value can't be null or empty";

	public ValueObjectValidationException() {
		super(CODE, MESSAGE);
	}

	public ValueObjectValidationException(final String message) {
		super(CODE, message);
	}

	public ValueObjectValidationException(final String message, final Throwable cause) {
		super(CODE, message, cause);
	}

	public ValueObjectValidationException(final Throwable cause) {
		super(CODE, MESSAGE, cause);
	}
}
