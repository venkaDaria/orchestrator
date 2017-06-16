package com.globallogic.orchestrator.service.exception;

import com.globallogic.orchestrator.connector.exception.FileProcessException;

public class ServiceConfigurationException extends FileProcessException {
    private static final int CODE = 100104;
    private static final String MESSAGE = "Can't read service from file";

    public ServiceConfigurationException() {
        super(CODE, MESSAGE);
    }

    public ServiceConfigurationException(final String message) {
        super(CODE, message);
    }

    public ServiceConfigurationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public ServiceConfigurationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }
}
