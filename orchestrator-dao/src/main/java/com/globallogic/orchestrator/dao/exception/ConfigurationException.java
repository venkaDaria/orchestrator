package com.globallogic.orchestrator.dao.exception;

import com.globallogic.orchestrator.base.exception.ApplicationException;

/**
 * Exception's codes interval is 100200-100299
 */
public class ConfigurationException extends ApplicationException {
    private static final int CODE = 100200;
    private static final String MESSAGE = "Problems with configuration dto";

    public ConfigurationException() {
        super(CODE, MESSAGE);
    }

    public ConfigurationException(final String message) {
        super(CODE, message);
    }

    public ConfigurationException(final String message, final Throwable cause) {
        super(CODE, message, cause);
    }

    public ConfigurationException(final Throwable cause) {
        super(CODE, MESSAGE, cause);
    }

    public ConfigurationException(final int code, final String message) {
        super(code, message);
    }

    public ConfigurationException(final int code, final String message, final Throwable cause) {
        super(code, message, cause);
    }
}

