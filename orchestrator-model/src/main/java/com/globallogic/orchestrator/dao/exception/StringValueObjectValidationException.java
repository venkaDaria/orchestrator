package com.globallogic.orchestrator.dao.exception;

public class StringValueObjectValidationException extends ValidationException {
	private static final int CODE = 100008;
	private static final String MESSAGE = "Value can't be null or empty";

	public StringValueObjectValidationException() {
		super(CODE, MESSAGE);
	}

	public StringValueObjectValidationException(final String message) {
		super(CODE, message);
	}

	public StringValueObjectValidationException(final String message, final Throwable cause) {
		super(CODE, message, cause);
	}

	public StringValueObjectValidationException(final Throwable cause) {
		super(CODE, MESSAGE, cause);
	}
}
