package com.globallogic.orchestrator.exception;

public class ProtocolValidationException extends ValidationException {
	private static final int CODE = 100006;	
	private static final String MESSAGE = "Value can't be null or empty";
	
    public ProtocolValidationException() {
        super(CODE, MESSAGE);
    }

    public ProtocolValidationException(final String message) {
        super(CODE, message);
    }

    public ProtocolValidationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }
    
    public ProtocolValidationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}