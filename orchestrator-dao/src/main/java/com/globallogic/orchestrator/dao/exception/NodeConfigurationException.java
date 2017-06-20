package com.globallogic.orchestrator.dao.exception;

public class NodeConfigurationException extends ConfigurationException {
    private static final int CODE = 100202;
    private static final String MESSAGE = "Can't configure node";

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
