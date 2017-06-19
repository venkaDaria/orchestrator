package com.globallogic.orchestrator.exception;

public class ServiceConfigurationException extends ConfigurationException {
    private static final int CODE = 100203;
    private static final String MESSAGE = "Can't configure service";

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
