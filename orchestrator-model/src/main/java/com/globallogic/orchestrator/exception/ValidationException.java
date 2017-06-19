package com.globallogic.orchestrator.exception;

import com.globallogic.orchestrator.base.exception.ApplicationException;

/**
 * Exception's codes interval is 100001-100099
 */
public abstract class ValidationException extends ApplicationException {

	public ValidationException(final int code, final String message) {
		super(code, message);
	}

	public ValidationException(final int code, final String message, final Throwable cause) {
		super(code, message, cause);
	}
}