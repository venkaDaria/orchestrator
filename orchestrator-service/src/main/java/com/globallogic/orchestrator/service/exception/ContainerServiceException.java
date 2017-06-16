package com.globallogic.orchestrator.service.exception;

import com.globallogic.orchestrator.base.exception.FileProcessException;

public class ContainerServiceException extends FileProcessException {
    private static final int CODE = 100102;
    private static final String MESSAGE = "Can't read container from file";

    public ContainerServiceException() {
        super(CODE, MESSAGE);
    }

    public ContainerServiceException(final String message) {
        super(CODE, message);
    }

    public ContainerServiceException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public ContainerServiceException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}
