package com.globallogic.orchestrator.service.exception;

import com.globallogic.orchestrator.base.exception.FileProcessException;

public class NodeServiceException extends FileProcessException {
    private static final int CODE = 100103;
    private static final String MESSAGE = "Can't read node from file";

    public NodeServiceException() {
        super(CODE, MESSAGE);
    }

    public NodeServiceException(final String message) {
        super(CODE, message);
    }

    public NodeServiceException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public NodeServiceException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}
