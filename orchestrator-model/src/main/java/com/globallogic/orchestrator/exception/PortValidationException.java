package com.globallogic.orchestrator.exception;

import com.globallogic.orchestrator.base.ValidationException;

public class PortValidationException extends ValidationException {
	private static final int CODE = 100004;	
	private static final String MESSAGE = "Port must be \"int:int/protocol\" or \"int/protocol\"";
	
    public PortValidationException() {
        super(CODE, MESSAGE);
    }

    public PortValidationException(final String message) {
        super(CODE, message);
    }

    public PortValidationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }
    
    public PortValidationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}