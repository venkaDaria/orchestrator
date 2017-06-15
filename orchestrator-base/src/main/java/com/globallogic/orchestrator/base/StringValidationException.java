package com.globallogic.orchestrator.base;

public class StringValidationException extends ValidationException {
	private static final int CODE = 100008;
	private static final String MESSAGE = "Value can't be null or empty";

	public StringValidationException() {
		super(CODE, MESSAGE);
	}

	public StringValidationException(final String message) {
		super(CODE, message);
	}

	public StringValidationException(final String message, final Throwable cause) {
		super(CODE, message, cause);
	}

	public StringValidationException(final Throwable cause) {
		super(CODE, MESSAGE, cause);
	}
}
