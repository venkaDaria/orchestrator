package com.globallogic.orchestrator.service.exception;

import com.globallogic.orchestrator.base.exception.FileProcessException;

public class ServiceServiceException extends FileProcessException {
    private static final int CODE = 100104;
    private static final String MESSAGE = "Can't read service from file";

    public ServiceServiceException() {
        super(CODE, MESSAGE);
    }

    public ServiceServiceException(final String message) {
        super(CODE, message);
    }

    public ServiceServiceException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public ServiceServiceException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}
