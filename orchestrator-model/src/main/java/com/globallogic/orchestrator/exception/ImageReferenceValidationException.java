package com.globallogic.orchestrator.exception;

import com.globallogic.orchestrator.base.ValidationException;

public class ImageReferenceValidationException extends ValidationException {
	private static final int CODE = 100003;	
	private static final String MESSAGE = "ImageReference must be: \"server/name:tag@digestTag\"";
	
    public ImageReferenceValidationException() {
        super(CODE, MESSAGE);
    }

    public ImageReferenceValidationException(final String message) {
        super(CODE, message);
    }

    public ImageReferenceValidationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }
    
    public ImageReferenceValidationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}