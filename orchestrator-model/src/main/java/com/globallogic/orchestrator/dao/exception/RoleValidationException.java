package com.globallogic.orchestrator.dao.exception;

public class RoleValidationException extends ValidationException {
	private static final int CODE = 100007;	
	private static final String MESSAGE = "Value can't be null or empty";
	
    public RoleValidationException() {
        super(CODE, MESSAGE);
    }

    public RoleValidationException(final String message) {
        super(CODE, message);
    }

    public RoleValidationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }
    
    public RoleValidationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}