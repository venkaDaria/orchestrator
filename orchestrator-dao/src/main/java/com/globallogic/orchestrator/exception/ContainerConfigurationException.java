package com.globallogic.orchestrator.exception;

public class ContainerConfigurationException extends ConfigurationException {
    private static final int CODE = 100201;
    private static final String MESSAGE = "Can't configure container";

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
