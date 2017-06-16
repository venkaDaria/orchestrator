package com.globallogic.orchestrator.service.exception;

import com.globallogic.orchestrator.connector.exception.FileProcessException;

public class ContainerConfigurationException extends FileProcessException {
    private static final int CODE = 100102;
    private static final String MESSAGE = "Can't read container from file";

    public ContainerConfigurationException() {
        super(CODE, MESSAGE);
    }

    public ContainerConfigurationException(final String message) {
        super(CODE, message);
    }

    public ContainerConfigurationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public ContainerConfigurationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}
