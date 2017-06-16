package com.globallogic.orchestrator.service.exception;

import com.globallogic.orchestrator.connector.exception.FileProcessException;

public class NodeConfigurationException extends FileProcessException {
    private static final int CODE = 100103;
    private static final String MESSAGE = "Can't read node from file";

    public NodeConfigurationException() {
        super(CODE, MESSAGE);
    }

    public NodeConfigurationException(final String message) {
        super(CODE, message);
    }

    public NodeConfigurationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public NodeConfigurationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}
