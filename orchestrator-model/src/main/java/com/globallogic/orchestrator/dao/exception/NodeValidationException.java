package com.globallogic.orchestrator.dao.exception;

public class NodeValidationException extends ValidationException {
	private static final int CODE = 100002;	
	private static final String MESSAGE = "Can't do some operation with node";
	
    public NodeValidationException() {
        super(CODE, MESSAGE);
    }

    public NodeValidationException(final String message) {
        super(CODE, message);
    }

    public NodeValidationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }
    
    public NodeValidationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}